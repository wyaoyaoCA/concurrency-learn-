package study.wyy.concurrency.thread.api.daemon_test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-26 22:07
 * @description：守护线程测试1---守护线程和非守护线程的区别

 */
@Slf4j
public class DaemonThreadTest1 {

    public static void main(String[] args) {
        Thread thread = new Thread("MyThread"){
            @Override
            public void run() {
                try {
                    log.info("{} is running....", Thread.currentThread().getName());
                    Thread.sleep(10_000);
                    log.info("{} is done....", Thread.currentThread().getName());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        thread.start();

        // 主线程运行完下面一句，就结束了
        log.info("{} has finsh....", Thread.currentThread().getName());
    }
}
