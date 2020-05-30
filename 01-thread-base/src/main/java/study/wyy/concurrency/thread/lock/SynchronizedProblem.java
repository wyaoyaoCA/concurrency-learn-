package study.wyy.concurrency.thread.lock;

/**
 * @author ：wyy
 * @date ：Created in 2020-04-04 20:36
 * @description：synchronized问题演示: 不能打断的问题
 * @modified By：
 * @version: $
 */
public class SynchronizedProblem {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {

                SynchronizedProblem.run();
            }
        };
        t1.start();

        Thread.sleep(1000);
        // 开启一个线程去执行run方法，此时肯定会block住，因为t1没有释放锁，一直在运行
        Thread t2 = new Thread("t2"){
            @Override
            public void run() {

                SynchronizedProblem.run();
            }
        };
        t2.start();
        //假设等了2秒，t1还没有执行结束，没释放锁，t2还没有抢到执行权
        // 就打断一下t2，不再进行抢锁，但是interrupt只能种下一个标志而已，无法让t2返回，不再去抢锁
        t2.interrupt();
        System.out.println(t2.isInterrupted());

    }

    private synchronized static void run(){
        System.out.println(Thread.currentThread().getName() + " is running .....");
        // 模拟线程一直没有释放锁
        while (true){

        }
    }
}
