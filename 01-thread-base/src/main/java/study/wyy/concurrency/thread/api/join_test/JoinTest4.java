package study.wyy.concurrency.thread.api.join_test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 09:27
 * @description： join 的重载方法  join(long millis)
 * 父线程最多等待子线程millis毫秒，如果millis毫秒内，子线程的任务依旧没有执行结束，父线程就不再等，会去执行自己的剩下的逻辑
 */
@Slf4j
public class JoinTest4 {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程，
        Thread thread = new Thread(() -> {
            try {
                log.info("{} task is start ...", Thread.currentThread().getName());
                Thread.sleep(10_000);
                log.info("{} task is done ...", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "MyThread");
        thread.start();
        // 父线程（这里就是主线程）等待5毫秒
        thread.join(5_000);

        log.info("5 毫秒已经过去 {}不再等待子线程的执行",Thread.currentThread().getName());

    }
}
