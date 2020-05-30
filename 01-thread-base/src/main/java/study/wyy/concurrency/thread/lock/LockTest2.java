package study.wyy.concurrency.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

/**
 * @author ：wyy
 * @date ：Created in 2020-04-04 10:25
 * @description：测试
 * @modified By：
 * @version: $
 */
@Slf4j
public class LockTest2 {


    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new BooleanLock();
        // 创建四个线程
        Stream.of("T1", "T2", "T3", "T4").forEach(t -> {
            new Thread(t) {
                @Override
                public void run() {
                    try {
                        lock.lock(10L);
                        log.info("{} have the lock", Thread.currentThread().getName());
                        excute();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Lock.TimeOutException e) {
                        log.error("{} get the lock time out ",Thread.currentThread().getName());
                    } finally {
                        lock.unLock();
                    }
                }
            }.start();
        });
    }


    /**
     * 模拟线程要执行的任务
     */
    private static void excute() throws InterruptedException {
        log.info("{} is excute start.....", Thread.currentThread().getName());
        Thread.sleep(10_000);
        log.info("{} is excute finsh.....", Thread.currentThread().getName());
    }
}
