package study.wyy.concurrency.thread.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-24 20:21
 * @description：使用runnable接口实现
 */
public class CreateThreadDemo2 {
    public static void main(String[] args) {
        // 1 定义一份业务数据
        final TicketWindowRunnable windowRunnable = new TicketWindowRunnable();
        // 2 开启三个线程，每个线程都持有上面的这一份业务数据
        new Thread(windowRunnable, "1号窗口").start();
        new Thread(windowRunnable, "2号窗口").start();
        new Thread(windowRunnable, "3号窗口").start();
    }
}

@Slf4j
class TicketWindowRunnable implements Runnable {

    private Integer sum = 50;
    private Integer index = 1;

    @Override
    public void run() {
        while (index <= sum) {
            log.info("{}售卖第{}张票", Thread.currentThread().getName(), index);
            index++;
        }
    }
}

