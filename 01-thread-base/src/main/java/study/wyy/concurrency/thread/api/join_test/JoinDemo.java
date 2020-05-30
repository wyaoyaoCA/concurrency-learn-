package study.wyy.concurrency.thread.api.join_test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 10:49
 * @description：Join的demo演示 【说明】： 我们需要采集三个机器的数据，采集完三个机器的数据之后，统一进行保存
 * 准备使用三个线程分别对三个机器进行采集
 */
@Slf4j
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        CaptureDataTask task1 = new CaptureDataTask("机器1", 10000L);
        Thread thread1 = new Thread(task1);
        CaptureDataTask task2 = new CaptureDataTask("机器2", 20000L);
        Thread thread2 = new Thread(task2);
        CaptureDataTask task3 = new CaptureDataTask("机器3", 30000L);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        Data result1 = task1.getResult();
        Data result2 = task2.getResult();
        Data result3 = task3.getResult();
        log.info("开始保存数据：[{}],[{}],[{}]", result1, result2, result3);
    }
}

