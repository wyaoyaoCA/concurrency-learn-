package study.wyy.concurrency.thread.api;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-29 19:55
 * @description：sleep和wait区别 - sleep是Thread的方法，wait是Object的方法
 * - sleep不会释放锁，但是wait会释放锁，并且将线程添加到该对象监视器（monitor）的等待队列中
 * - sleep使用的时候不依赖同步锁，而使用wait的时候必须加同步锁
 * - sleep不需要被唤醒，但是wait是需要唤醒的
 */
public class DifferenceOfWaitAndSleep {

    private final static Object LOCK = new Object();

    public static void main(String[] args) {
        // m1();
        //m2();
        m3();
    }


    public static void m1() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 这里没有加锁，运行，就会抛出一个异常IllegalMonitorStateException
    public static void m2() {
        try {
            LOCK.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 这里加锁，运行，就不会抛出一个异常IllegalMonitorStateException
    public static void m3() {
        synchronized (LOCK){
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void m4() {
        synchronized (LOCK){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
