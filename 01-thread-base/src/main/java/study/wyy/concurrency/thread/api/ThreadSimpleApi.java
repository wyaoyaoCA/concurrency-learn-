package study.wyy.concurrency.thread.api;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-27 20:22
 * @description：测试Thread id，name ，优先级
 * @modified By：
 * @version: $
 */
@Slf4j
public class ThreadSimpleApi  {

    public static void main(String[] args) {
        // 1 创建一个线程
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(3_000);
            }catch (Exception e){
                e.printStackTrace();
            }
        },"MyThread");

        // 日志打印 线程的名字，id，默认优先级
        log.info("线程名字：{}",thread.getName());
        log.info("线程id：{}",thread.getId());
        log.info("线程默认优先级：{}",thread.getPriority());
    }

}
