package study.wyy.concurrency.thread.api;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-29 19:55
 * @description：sleep和wait区别 - sleep是Thread的方法，wait是Object的方法
 * - sleep不会释放锁，但是wait会释放锁，并且将线程添加到该对象监视器（monitor）的等待队列中
 * - sleep使用的时候不依赖同步锁，而使用wait的时候必须加同步锁
 * - sleep不需要被唤醒，但是wait是需要唤醒的
 */
@Slf4j
public class DifferenceOfWaitAndSleep2 {

    private final static Object LOCK = new Object();

    public static void main(String[] args) {
        // 运行发现，T1进去之后休眠3秒，T2才会执行，也就是说T1 Sleep的时候没有释放锁，T2抢不到执行权
        /*Stream.of("T1","T2").forEach(t -> {
            new Thread(t){
                @Override
                public void run() {
                    m1();
                }
            }.start();
        });*/
       // T1和T2几乎同时enter，也就是说无论T1还是T2先抢到执行权，wait之后，立马就会释放锁，剩下的那个就会抢到执行权执行
        // 所以几乎同时enter
        Stream.of("T1","T2").forEach(t -> {
            new Thread(t){
                @Override
                public void run() {
                    m2();
                }
            }.start();
        });

    }


    public static void m1() {
        synchronized (LOCK){
            try {
                log.info("{} enter");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static void m2() {
        synchronized (LOCK){
            try {
                log.info("{} enter");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
