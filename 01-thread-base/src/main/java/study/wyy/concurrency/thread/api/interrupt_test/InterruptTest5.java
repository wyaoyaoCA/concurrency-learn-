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
public class InterruptTest5 {


    /**(=
     * 如果该线程处于阻塞状态（sleep，wait，join），此时打断该线程，则会捕捉到InterruptedException异常，它的中断状态将被清除
     */

    private static final Object MONITOR = new Object();
    public static void main(String[] args) throws InterruptedException {
        // 定义一个线程
        Thread thread = new Thread("MyThread"){
            @Override
            public void run() {
                while (true){
                    synchronized (MONITOR){
                        try {
                            MONITOR.wait(10_000);
                        } catch (InterruptedException e) {
                            log.info("{} 的Interrupted标记: {}",this.getName(),this.isInterrupted());
                            log.info("捕捉到打断信号 ");
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        thread.start();
        // 主线程1s后打断线程
        Thread.sleep(1_000);
        thread.interrupt();
    }
}
