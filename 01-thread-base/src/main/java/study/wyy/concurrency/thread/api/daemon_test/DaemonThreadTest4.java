package study.wyy.concurrency.thread.api.daemon_test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-26 22:07
 * @description：守护线程测试4---start之后调用setDaemon

 */
@Slf4j
public class DaemonThreadTest4 {

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
        // MyThread 设置为守护线程
        //thread.checkAccess();
        thread.start();
        try {
            Thread.sleep(2000);
            thread.checkAccess();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
