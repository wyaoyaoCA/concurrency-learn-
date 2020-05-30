package study.wyy.concurrency.thread.api.join_test;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 09:27
 * @description：
 * 主线程中创建多个线程
 */
@Slf4j
public class JoinTest3 {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程，打印1 到1000
        Thread thread1 = new Thread(() -> {
            try {
                IntStream.range(1, 1000).forEach(i -> log.info("{}->{}", Thread.currentThread().getName(), i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"MyThread1");

        Thread thread2 = new Thread(() -> {
            try {
                IntStream.range(1, 1000).forEach(i -> log.info("{}->{}", Thread.currentThread().getName(), i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"MyThread2");
        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();
        log.info("ALL child tasks finsh done");
        // 主线程进行同样的操作
        IntStream.range(1, 1000).forEach(i -> log.info("{}->{}", Thread.currentThread().getName(), i));
    }
}
