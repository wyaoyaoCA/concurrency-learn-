package study.wyy.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-05 10:51
 * 增加shutdown
 */
@Slf4j
public class SimpleThreadPool3 {

    // 线程的数量
    private final int size;

    // 任务队列的大小
    private final int queueSize;

    // 默认线程的数量
    private final static int DEFAULT_SIZE = 10;

    // 默认任务队列的大小
    private final static int DEFAULT_QUEUE_SIZE = 2000;

    private static volatile int seq = 0;

    // 线程池里线程名字的前缀
    private final static String THREAD_NAME_PREFIX = "SIMPLE_THREAD_POOL-";

    // 线程组
    private final static ThreadGroup THREAD_GROUP = new ThreadGroup("Pool_Group");

    // 任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    // 用于管理线程
    private final static List<ExcuteTask> THREAD_QUEUE = new ArrayList<>();

    // 拒绝策略
    private final DisCardPolicy disCardPolicy;

    // 线程池是否销毁
    private volatile boolean destroy = false;

    public static final DisCardPolicy DEFAULT_DISCARD_POLICY = ()->{
        throw new DisCardException("Task is disCard");
    };


    public SimpleThreadPool3() {
        this.queueSize = DEFAULT_QUEUE_SIZE;
        this.size = DEFAULT_SIZE;
        this.disCardPolicy = DEFAULT_DISCARD_POLICY;
        init();
    }

    public SimpleThreadPool3(int size, int queueSize, DisCardPolicy disCardPolicy) {
        this.size = size;
        this.queueSize = queueSize;
        this.disCardPolicy = disCardPolicy;
        init();
    }

    // 提交任务
    public void submit(Runnable runnable) {
        if(destroy){
            throw new IllegalStateException("thread pool has destroy");
        }
        synchronized (TASK_QUEUE) {
            if(TASK_QUEUE.size()>queueSize){
                disCardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            // 通知那些wait的线程，有任务了
            TASK_QUEUE.notifyAll();
        }
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createExcuteTask();
        }
    }

    private void createExcuteTask() {
        ExcuteTask excuteTask = new ExcuteTask(THREAD_GROUP, THREAD_NAME_PREFIX + (seq++));
        // 启动
        excuteTask.start();
        THREAD_QUEUE.add(excuteTask);
    }


    // shutdown
    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()){
            // 如果任务队列不是空，说明还有任务未执行结束不能关闭
            // 就休眠一下，不要一直在这判断
            Thread.sleep(50);
        }
        // 任务队列已经空了，所有的任务已经开始执行，但不代表任务完成
        // 因为是异步的，可能还有任务还在running
        // 根据线程池管理的线程数来进行操作
        int threadSize = THREAD_QUEUE.size();
        while (threadSize > 0){
            // 遍历线程池里创建的线程
            for(ExcuteTask task: THREAD_QUEUE){
                if(task.taskStatue == TaskStatue.BLOCKED){
                    // 如果是BLOCKED状态 ==》 就是在wait的时候（任务队列是空的时候，任务已经全部开始执行）
                    // 打断线程, 根据下面的run方法，在打断的时候会捕获到InterruptedException， 跳出到Outer的位置
                    // 跳出到Outer的位置: 这个线程的的run方法也就结束了
                    task.interrupt();
                    // 关闭这个线程（在打断的时候，有可能线程不是在wait，所以就不无法捕获InterruptedException，
                    // 这个线程下次唤醒的时候，又会进入while，所以这里将Thread的状态改为Dead）
                    /**
                     * 打断的时候，线程可能润兴在这里，就无法捕捉InterruptedException异常
                     *  if (runnable != null) {
                     *                     taskStatue = TaskStatue.RUNNING;
                     *                     runnable.run();
                     *                     taskStatue = TaskStatue.Free;
                     *                 }
                     */
                    task.close();
                    threadSize--;
                }else{
                    // 否则，就休息一下，不要疯狂运行
                    Thread.sleep(50);
                }
            }
        }
        this.destroy = true;
        log.info("ThreadPool has disposed");
    }

    // 是否销毁
    public boolean isDestroy(){
        return this.destroy;
    }

    // 封装Thread
    private static class ExcuteTask extends Thread {
        // 创建任务的时候默认是Free状态
        private volatile TaskStatue taskStatue = TaskStatue.Free;

        public ExcuteTask(ThreadGroup group, String name) {
            super(group, name);
        }


        // 重写run方法，不能让线程执行结束后，被jvm回收
        @Override
        public void run() {
            OUTER:
            while (this.taskStatue != TaskStatue.DEAD) {
                Runnable runnable;
                // 这个线程没有死掉，就去任务队列里取任务执行
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        // 空的，说明没有提交任务，或者任务已经全部开始执行了，那么就只能等待（放到了TASK_QUEUE的wait队列中）
                        try {
                            taskStatue = TaskStatue.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            // wait的时候，如果别人打断线程，要跳出到OUTER位置
                            break OUTER;
                        }
                    }
                    // 唤醒了，抢到了锁，任务队列不是空，就要执行任务，
                    runnable = TASK_QUEUE.removeFirst();
                }
                // 一定要主要同步的范围，获取任务是需要同步的，但是任务的执行，就不能加锁了，否则就没有意义了
                // 因为会一个个的同步执行。！！！！！
                if (runnable != null) {
                    taskStatue = TaskStatue.RUNNING;
                    runnable.run();
                    taskStatue = TaskStatue.Free;
                }
            }
        }

        // 返回任务的状态
        public TaskStatue getTaskStatue() {
            return this.taskStatue;
        }

        // 关闭任务
        public void close() {
            this.taskStatue = TaskStatue.DEAD;
        }

    }


    // 枚举：线程的状态
    private enum TaskStatue {
        Free,
        RUNNING,
        BLOCKED,
        DEAD
    }

    // 拒绝策略，定义成public，可以由使用者自定义满足使用需求的策略
    @FunctionalInterface
    public interface DisCardPolicy {
        void discard() throws DisCardException;
    }

    // 定义一个异常
    public static class DisCardException extends RuntimeException {
        public DisCardException(String message) {
            super(message);
        }
    }

}
