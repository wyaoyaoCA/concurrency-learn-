package study.wyy.concurrency.thread.api;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-25 21:30
 * @description：
 * @modified By：
 * @version: $
 */
@Slf4j
public class ThreadGroupTest {

    public static void main(String[] args) {
        Thread thread = new Thread("my-thread"){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

        int i = threadGroup.activeCount();
        log.info("threadGroup.activeCount() => {}",i);

        // ThreadGroup中有个方法，可以枚举出组里的线程
        Thread[] threads = new Thread[i];
        threadGroup.enumerate(threads);
        // 遍历
        Arrays.asList(threads).forEach(System.out::println);


    }
}
