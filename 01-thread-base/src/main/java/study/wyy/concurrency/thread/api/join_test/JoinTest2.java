package study.wyy.concurrency.thread.api.join_test;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 09:27
 * @description：
 * 使用join的时候，主线程会等MyThread执行结束
 */
@Slf4j
public class JoinTest2 {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程，打印1 到1000
        Thread thread = new Thread(() -> {
            try {
                IntStream.range(1, 1000).forEach(i -> log.info("{}->{}", Thread.currentThread().getName(), i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"MyThread");
        thread.start();
        thread.join();
        // 主线程进行同样的操作
        IntStream.range(1, 1000).forEach(i -> log.info("{}->{}", Thread.currentThread().getName(), i));
    }
}
