package study.wyy.concurrency.guared.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author by wyaoyao
 * @Description
 * @Date 2021/1/20 8:22 下午
 */
@Slf4j
public class Test2 {
    public static void main(String[] args) {
        GuardObject<String> guardObject = new GuardObject<>();
        Random random = new Random();

        new Thread(()->{
            try {
                log.info("开始获取结果");
                String res = guardObject.get(3);
                log.info("拿到结果: => " + res);
            } catch (TimeoutException e) {
                log.error("time out,",e);
            }
        },"t1").start();
        new Thread(()->{
            log.info("开始准备结果");
            sleepSecond(5);
            guardObject.submit("哈哈哈哈哈");
        },"t2").start();
    }

    private static void sleepSecond(int i)  {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
