package study.wyy.concurrency.threadgroup;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-04-04 23:40
 * @description：
 * @modified By：
 * @version: $
 */
@Slf4j
public class CreateThreadGroup {

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("TG1");
        Thread thread = new Thread(threadGroup,"T1"){
            @Override
            public void run() {
               log.info("T1的 ThreadGroup=> {}",Thread.currentThread().getThreadGroup().getName());
               log.info("TG1的 父ThreadGroup=> {}",Thread.currentThread().getThreadGroup().getParent().getName());
            }
        };
        thread.start();
    }
}
