package study.wyy.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-05 15:25
 */
@Slf4j
public class Test2 {

    public static void main(String[] args) {
        SimpleThreadPool2 simpleThreadPool = new SimpleThreadPool2(6,10,SimpleThreadPool2.DEFAULT_DISCARD_POLICY);
         // 创建40个任务
        IntStream.range(0,40).forEach(i->{
            System.out.println(i);
            simpleThreadPool.submit(()->{

                log.info("任务{}开始,负责执行当前任务的的线程是 {}",i,Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("任务{}完成,负责执行当前任务的的线程是 {}",i,Thread.currentThread().getName());
            });
        });
    }
}
