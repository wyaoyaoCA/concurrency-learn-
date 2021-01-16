package study.wyy.concurrency.async;

import lombok.extern.slf4j.Slf4j;

/**
 * @author by wyaoyao
 * @Description
 * @Date 2021/1/11 8:40 下午
 */
@Slf4j
public class Demo01 {
    // 定义一个线程共享数据
    static int counter = 0;

    private static Object lock = new Object();
    private static Object lock2 =  new Object();

    public static void main(String[] args) throws InterruptedException {
        // 定义一个线程进行++操作
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                // 加锁，用的是lock
                synchronized (lock){
                    counter++;
                }
            }
        }, "t1");
        // 定义一个线程进行--操作
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                // 加锁，用的是lock2
                synchronized (lock2){
                    counter--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();
        // 让主线程等待这两个线程结束，让这两个线程join进来
        t1.join();
        t2.join();
        log.info("counter: {}", counter);
    }



}
