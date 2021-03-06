package study.wyy.concurrency.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-05 10:51
 * 增加拒绝策略：
 */
public class SimpleThreadPool2 {

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

    public static final DisCardPolicy DEFAULT_DISCARD_POLICY = ()->{
        throw new DisCardException("Task is disCard");
    };


    public SimpleThreadPool2() {
        this.queueSize = DEFAULT_QUEUE_SIZE;
        this.size = DEFAULT_SIZE;
        this.disCardPolicy = DEFAULT_DISCARD_POLICY;
        init();
    }

    public SimpleThreadPool2(int size, int queueSize, DisCardPolicy disCardPolicy) {
        this.size = size;
        this.queueSize = queueSize;
        this.disCardPolicy = disCardPolicy;
        init();
    }

    // 提交任务
    public void submit(Runnable runnable) {
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
                        // 空的，说明没有提交任务，那么就只能等待（放到了TASK_QUEUE的wait队列中）
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
