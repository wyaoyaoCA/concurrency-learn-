package study.wyy.concurrency.thread.api.close;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 19:33
 * @description：自定义线程任务
 * @modified By：
 * @version: $
 */
@Slf4j
public class ThreadTask implements Runnable {

    private volatile boolean start = true;
    @Override
    public void run() {
        while (start){
            try {
                Thread.sleep(2_000);
                log.info("{} is runniung...", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        this.start = false;
    }
}
