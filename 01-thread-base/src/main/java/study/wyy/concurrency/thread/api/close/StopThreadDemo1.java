package study.wyy.concurrency.thread.api.close;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 19:32
 * @description：停止线程
 * @modified By：
 * @version: $
 */

public class StopThreadDemo1 {

    public static void main(String[] args) throws InterruptedException {
        ThreadTask threadTask = new ThreadTask();
        Thread thread = new Thread(threadTask);
        thread.start();

        // 主线程休眠10s后，停止ThreadTask
        Thread.sleep(10_000);

        threadTask.shutdown();

    }
}
