package study.wyy.concurrency.guared.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * @author by wyaoyao
 * @Description
 * @Date 2021/1/20 8:22 下午
 */
@Slf4j
public class Test3 {
    public static void main(String[] args) {
        GuardObject<String> guardObject = new GuardObject<>();
        Random random = new Random();

        new Thread(()->{
            while (true){
                log.info("开始获取结果");
                String res = guardObject.get();
                sleepSecond(2);
                log.info("拿到结果: => " + res);
            }
        },"t1").start();

        // 10个线程来生产数据
        IntStream.range(1,10).forEach(i->
            new Thread(()->{
                guardObject.submit("第"+ i + "个数据");
            },Integer.toString(i)).start()
        );
    }

    private static void sleepSecond(int i)  {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
