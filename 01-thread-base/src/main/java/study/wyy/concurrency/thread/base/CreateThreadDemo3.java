package study.wyy.concurrency.thread.base;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-18 21:28
 * @description：
 * @modified By：
 * @version: $
 */
@Slf4j
public class CreateThreadDemo3 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                log.info("running ......");
                return 100;
            }
        });

        Thread t = new Thread(task);
        t.start();

        // 如何获取 task返回值100呢
        // 假设现在在主线程中，要获取task的返回值, 调用get方法
        // 当主线程执行到此，就会阻塞，等待task返回
        Integer value = task.get();
        log.info("task返回值: {}", value);

    }
}
