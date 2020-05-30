package study.wyy.concurrency.thread.api.communication;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-29 12:03
 * @description：多生产者多消费者-》问题演示
 * @modified By：
 * @version: $
 */
@Slf4j
public class ProduceConsumerDemo3 {

    // 数据
    private int data = 0;

    final private Object lock = new Object();

    // 标志：是否已经生产数据了
    private volatile Boolean hasProduce = false;

    // 生产数据
    private void produce() {
        synchronized (lock) {
            if (hasProduce) {
                // 已经生产了数据，则等待，生产者进入阻塞状态
                log.info("{}正在等待消费者消费数据。。。", Thread.currentThread().getName());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // 没有生产过数据（没有数据等待消费）
                log.info("{}线程开始生产数据 => {}", Thread.currentThread().getName(), ++data);
                hasProduce = Boolean.TRUE;
                // 生产完数据，则通知消费者（唤醒消费者）
                lock.notify();
            }
        }
    }

    // 生产数据
    private void consumer() {
        synchronized (lock) {
            if (hasProduce) {
                log.info("{}线程消费数据 => {}", Thread.currentThread().getName(), data);
                // 消费结束，通知生产者生产数据
                hasProduce = Boolean.FALSE;
                lock.notify();
            } else {
                log.info("{}正在等待生产者消生产数据。。。", Thread.currentThread().getName());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 测试
    public static void main(String[] args) {
        ProduceConsumerDemo3 pc = new ProduceConsumerDemo3();
        // 开启liang个线程生产数据， p1，p2
        Stream.of("P1", "P2").forEach(t -> {
            new Thread(() -> {
                while (true) {
                    pc.produce();
                }
            }, t).start();
        });

        // 开启两个线程消费数据
        Stream.of("T1", "T2").forEach(t -> {
            new Thread(() -> {
                while (true) {
                    pc.consumer();
                }
            }, t).start();
        });

    }
}
