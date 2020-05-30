package study.wyy.concurrency.thread.hi_volatile;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-11 11:34
 *
 */
@Slf4j
public class VolatileTest2 {

    private static int INII_VALUE = 0;
    private static int MAX_LIMIT = 50;


    public static void main(String[] args) {

        new Thread(() -> {

            while (INII_VALUE < MAX_LIMIT) {
                System.out.println("T1->" + (++INII_VALUE));
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "THREAD1").start();



        new Thread(() -> {
            while (INII_VALUE < MAX_LIMIT) {
                System.out.println("T2->" + (++INII_VALUE));
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "THREAD2").start();


    }
}
