package study.wyy.concurrency.thread.api.daemon_test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-26 22:07
 * @description：

 */
@Slf4j
public class DaemonThreadTest3 {

    public static void main(String[] args) {
        Thread thread = new Thread("父线程"){
            @Override
            public void run() {
                // 在开启一个子线程。进行心跳检查
                Thread innerThread = new Thread("子线程"){
                    @Override
                    public void run() {
                        try {
                            while (true){
                                log.info("do some thing for health check");
                                Thread.sleep(1000);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                //innerThread.setDaemon(Boolean.TRUE);
                innerThread.start();
                try {
                    // 父线程 休眠5s。就退出
                    Thread.sleep(5000);
                    log.info("父线程 has done。。。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
