package study.wyy.concurrency.thread.api.interrupt_test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 14:28
 * @description：中断线程
 * @modified By：
 * @version: $
 */
@Slf4j
public class InterruptTest2 {

    public static void main(String[] args) throws InterruptedException {
        // 定义一个线程
        Thread thread = new Thread("MyThread"){
            @Override
            public void run() {
                while (true){
                    // 线程内部 this就是指当前线程，和Thread.getCurrentThread是一样的
                    log.info("{} 的Interrupted标记: {}",this.getName(),this.isInterrupted());
                }
            }
        };
        thread.start();
        // 主线程10s后打断线程
        Thread.sleep(1_000);
        thread.interrupt();

    }
}
