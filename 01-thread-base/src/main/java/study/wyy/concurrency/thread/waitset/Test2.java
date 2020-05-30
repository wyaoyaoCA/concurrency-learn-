package study.wyy.concurrency.thread.waitset;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-11 10:21
 */
@Slf4j
public class Test2 {
    // 定义一个锁
    private static final Object LOCK = new Object();

    private static void work(){

        synchronized (LOCK){
            log.info("begining ......");
            try {
                log.info("Thread will coming");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("Thread will out");
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            work();
        }).start();
        Thread.sleep(1000);
        synchronized (LOCK){
            LOCK.notify();
        }
    }
}
