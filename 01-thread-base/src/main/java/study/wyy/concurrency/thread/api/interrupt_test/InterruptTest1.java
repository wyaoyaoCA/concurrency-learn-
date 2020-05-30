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
public class InterruptTest1 {

    public static void main(String[] args) {
        // 定义一个线程
        Thread thread = new Thread("MyThread"){
            @Override
            public void run() {
                while (true){

                }
            }
        };
        thread.start();
        log.info("{} 是否已经打断: {}",thread.getName(),thread.isInterrupted());
        // 打断MyThread线程
        thread.interrupt();
        log.info("{} 是否已经打断: {}",thread.getName(),thread.isInterrupted());
        // 虽然这里MyThread线程被打断了，依然在运行
    }
}
