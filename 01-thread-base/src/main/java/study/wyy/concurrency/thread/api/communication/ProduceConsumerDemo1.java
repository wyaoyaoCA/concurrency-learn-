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
public class ProduceConsumerDemo1 {

    // 数据
    private int data = 1;

    final private Object lock = new Object();

    // 生产数据
    private void produce(){
        synchronized (lock){
            log.info("{}线程生产了数据 => {}",Thread.currentThread().getName(),data++);
        }
    }

    // 生产数据
    private void consumer(){
        synchronized (lock){
            log.info("{}线程消费了数据 => {}",Thread.currentThread().getName(),data);
        }
    }

    // 测试
    public static void main(String[] args) {
        ProduceConsumerDemo1 pc = new ProduceConsumerDemo1();
        // 开启一个线程生产数据
        new Thread(()->{
            while (true){
                pc.produce();
            }
        }, "生产者").start();
        // 开启一个线程消费数据
        new Thread(()->{
            while (true){
                pc.consumer();
            }
        }, "消费者").start();

    }
}
