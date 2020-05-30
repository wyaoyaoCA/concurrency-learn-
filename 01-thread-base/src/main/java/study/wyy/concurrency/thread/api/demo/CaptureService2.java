package study.wyy.concurrency.thread.api.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-29 21:21
 * 1. 向10个数据源采集数据，创建10个线程分别去采集。
 * 但是呢，并不是线程越多越好，如果10个线程再跑，切换线程的的上下文也很消耗资源的
 * 一般我们会限制同时活跃的线程数，比如这里限制同时最多5个，
 * 如果已经有5个在采集数据了，则当其中一个线程采集完数据，运行结束的时候，才会允许下一个开始采集数据
 * <p>
 * 2. 当所有数据采集结束的时候，会对数据进行统一处理。
 */
@Slf4j
public class CaptureService2 {

    private final static Integer MAX_ACTIVE = 5;
    // 当前或许的线程
    private final static LinkedList<Thread> activeThreads = new LinkedList<>();
    public static void main(String[] args) {
        // 创建10个采集数据的线程任务
        List<Thread> tasks = new ArrayList<>();
        Arrays.asList("S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10").stream()
                .map(CaptureService2::createCaptureThread)
                .forEach(task->{
                    // 为了启动之后能够，进行join，这里将每个任务收集起来
                    // 注意：不可再这里进行join，在这join的话，第一个任务过来一调用join方法，main线程就会阻塞，其他的九个任务就无法启动了
                    tasks.add(task);
                    task.start();
                });
        // 调用join，让主线程等待所有采集任务完成采集数据
        tasks.forEach(task -> {
            try {
                task.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.info("所有的任务完成数据采集，进行数据处理。。。。。");
    }


    private static Thread createCaptureThread(String threadName) {
        return new Thread(() -> {
            synchronized (activeThreads) {
                // 这里用while，每次进来的时候都需要先检查一下
                while (activeThreads.size() >= MAX_ACTIVE) {
                    // 如果活跃的线程大于5，则等待其他正在运行的任务结束
                    try {
                        activeThreads.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 如果活跃的线程<5，
                activeThreads.addLast(Thread.currentThread());
            }
            // 采集数据的时候，不能放在同步代码块里，否则就是串行，每个线程采取数据就是要并行化采集的
            log.info("线程：{} 开始采集数据", Thread.currentThread().getName());
            try {
                Thread.sleep(new Random().nextInt(20_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程：{} 采集数据结束", Thread.currentThread().getName());
            // 这里就要价同步，activeThreads是我们的锁对象，是所有任务共享，针对它的操作就需要加锁
            synchronized (activeThreads){
                activeThreads.remove(Thread.currentThread());
                // 唤醒那些等待执行的任务
                activeThreads.notifyAll();
            }
        }, threadName);
    }

}
