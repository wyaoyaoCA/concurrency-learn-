package study.wyy.concurrency.thread.waitset;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-11 10:10
 */
@Slf4j
public class Test1 {

    // 定义一个锁
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 模拟9个线程，进行wait
        IntStream.rangeClosed(1, 9).forEach(i ->
            new Thread(String.valueOf(i)) {
                @Override
                public void run() {
                    synchronized (LOCK){
                        try {
                            log.info("{} will place to wait set",Thread.currentThread().getName());
                            LOCK.wait();
                            log.info("{} will leave off wait set",Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start());

        // 休眠一会，进行唤醒
        Thread.sleep(3000);

        IntStream.rangeClosed(1,10).forEach(i->{
            synchronized (LOCK){
                LOCK.notify();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
