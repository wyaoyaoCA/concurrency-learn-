package study.wyy.concurrency.thread.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-24 20:22
 * @description：创建线程方式1 继承thread 重写run方法
    三个窗口，同时售卖50张票
 */
@Slf4j
public class CreateThreadDemo1 {
    public static void main(String[] args) {
        new TicketWindow("1号窗口").start();
        new TicketWindow("2号窗口").start();
        new TicketWindow("3号窗口").start();
    }

}
@Slf4j
class TicketWindow extends Thread {
    // 为了保证三个窗口是持有的是同一份数据（50张票）
    private static Integer sum = 50;
    private static Integer index = 1;

    public TicketWindow(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (index <= sum){
            log.info("{}售卖第{}张票",Thread.currentThread().getName(),index);
            index++;
        }
    }
}
