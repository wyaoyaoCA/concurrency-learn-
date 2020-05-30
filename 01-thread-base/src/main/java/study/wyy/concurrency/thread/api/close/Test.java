package study.wyy.concurrency.thread.api.close;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 21:22
 * @description：
 * @modified By：
 * @version: $
 */
@Slf4j
public class Test {

    // 测试超时
   /* public static void main(String[] args) {

        ThreadService threadService = new ThreadService();
        Long start = System.currentTimeMillis();
        threadService.excute(() -> {
            // 模拟一下，任务是在加载一个很耗时的资源
            while (true) {
                try {
                    log.info("load data .....");
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 我们期望在10s内结束，也就是超时时间为10s
        threadService.shutdown(10_000);
        Long end = System.currentTimeMillis();
        System.out.println("任务一共执行了多长时间: " + (end - start));
    }*/

   // 测试任务执行没有超时
    public static void main(String[] args) {

        ThreadService threadService = new ThreadService();
        Long start = System.currentTimeMillis();
        threadService.excute(() -> {
            // 模拟一下，任务是在加载一个很耗时的资源
            try {
                // 模拟一下，任务是6s就完成了
                log.info("load data .....");
                Thread.sleep(6_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 我们期望在10s内结束，也就是超时时间为10s
        threadService.shutdown(10_000);
        Long end = System.currentTimeMillis();
        System.out.println("任务一共执行了多长时间: " + (end - start));
    }
}
