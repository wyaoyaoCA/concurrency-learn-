package study.wyy.concurrency.guared.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author by wyaoyao
 * @Description
 * @Date 2021/1/19 9:15 下午
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        GuardObject<String> guardObject = new GuardObject<>();
        Random random = new Random();
        // 一个线程在从db进行数据的导出，
        // 另一个线程负责将导出的数据进行写入文件，
        new Thread(()->{
            log.info("写入文件前置处理");
            // 模拟前置处理的耗时
            sleepSecond(random.nextInt(5));
            // 获取另一个线程从数据库中读取到的数据
            String res = guardObject.get();
            log.info("开始写入文件。。。");
            // 模拟写入数据的耗时
            sleepSecond(random.nextInt(5));
            log.info("写入文件结束。。。文件内容: " + res);
        },"t1").start();
        new Thread(()->{
            // 读取数据库数据
            log.info("读取数据库数据开始");
            // 模拟读取数据的耗时
            sleepSecond(random.nextInt(5));
            String res = "hello word";
            log.info("读取数据库数据结束");
            // 提交数据
            guardObject.submit(res);
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
