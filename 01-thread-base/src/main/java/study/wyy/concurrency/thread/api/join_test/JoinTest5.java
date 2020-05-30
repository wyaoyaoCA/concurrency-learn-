package study.wyy.concurrency.thread.api.join_test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 09:27
 * @description：
 * 一个有趣的实验：
 */
@Slf4j
public class JoinTest5 {

    public static void main(String[] args) throws InterruptedException {
        // main线程等待main线程结束 -> 这里就不会结束，application不会死掉
       Thread.currentThread().join();
    }
}
