package study.wyy.concurrency.thread.api.close;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 19:32
 * @description：停止线程
 * @modified By：
 * @version: $
 */

public class StopThreadDemo2 {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        // 主线程休眠1s后，停止ThreadTask
        Thread.sleep(1_000);
        myThread.interrupt();

    }
    @Slf4j
    static class MyThread extends Thread{
        @Override
        public void run() {
            while (true){
                if(Thread.interrupted()){
                    log.info("MyThread线程结束。。。。。。");
                    break; // return
                }
                log.info("{} is runniung...", Thread.currentThread().getName());
            }
        }
    }
}
