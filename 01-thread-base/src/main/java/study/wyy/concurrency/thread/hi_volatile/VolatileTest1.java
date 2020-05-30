package study.wyy.concurrency.thread.hi_volatile;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-11 11:34
 * 定义两个线程一个线程负责读取数据，一个线程负责修改数据
 */
@Slf4j
public class VolatileTest1 {

    private volatile static int INII_VALUE = 0;
    private static int MAX_LIMIT = 5;


    public static void main(String[] args) {
        // 读线程
        new Thread(() -> {
            int localValue = INII_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INII_VALUE) {
                    // 不等于，说明INII_VALUE已经被另一个线程更新，就打印
                    log.info("The value has updated to [{}]", INII_VALUE);
                    localValue = INII_VALUE;
                }
            }
        }, "READER").start();


        // 写线程
        new Thread(() -> {
            while (INII_VALUE < MAX_LIMIT) {
                // 小于临界值的时候，就去更新
                INII_VALUE++;
                log.info("Update the value to [{}]",INII_VALUE);
                try {
                    // 这里休眠，是为了更好的演示效果，让Reader有时间去感知到数据的变化
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "WRITER").start();


    }
}
