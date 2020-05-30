package study.wyy.concurrency.thread.api.communication;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-29 12:03
 * @description：生产者消费者
 * @modified By：
 * @version: $
 */
@Slf4j
public class ProduceConsumerDemo2 {

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
                log.info("正在等待消费者消费数据。。。");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // 没有生产过数据（没有数据等待消费）
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
            if(hasProduce){
                log.info("{}线程消费数据 => {}",Thread.currentThread().getName(),data);
                // 消费结束，通知生产者生产数据
                hasProduce = Boolean.FALSE;
                lock.notify();
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                log.info("正在等待生产者消生产数据。。。");
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
        ProduceConsumerDemo2 pc = new ProduceConsumerDemo2();
        // 开启一个线程生产数据
        new Thread(() -> {
            while (true) {
                pc.produce();
            }
        }, "生产者").start();
        // 开启一个线程消费数据
        new Thread(() -> {
            while (true) {
                pc.consumer();
            }
        }, "消费者").start();

    }
}
