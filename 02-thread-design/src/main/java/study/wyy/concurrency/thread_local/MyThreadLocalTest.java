package study.wyy.concurrency.thread_local;

import lombok.extern.slf4j.Slf4j;

/**
 *  @author: wyaoyao
 *  @Date: 2020/9/12 5:59 下午
 *  @Description: 简单模拟一个ThreadLocal
 */
@Slf4j
public class MyThreadLocalTest {

    private static MyThreadLocal threadLocal = new MyThreadLocal<String>(){
        @Override
        public String initValue() {
            return "No Value";
        }
    };

    public static void main(String[] args) throws InterruptedException {
        // Thread-1
        Thread thread1 = new Thread(() -> {
            threadLocal.set("Thread-1");
            log.info("{} 线程中ThreadLocal获取的值为: {}", Thread.currentThread().getName(), threadLocal.get());
        }, "Thread-1");

        // Thread-2
        Thread thread2 = new Thread(() -> {
            threadLocal.set("Thread-2");
            log.info("{} 线程中ThreadLocal获取的值为: {}", Thread.currentThread().getName(), threadLocal.get());
        }, "Thread-2");
        thread1.start();
        thread2.start();

        // 为了防止主线程结束，而子线程还没有结束，让两个子线程join
        thread1.join();
        thread2.join();
        // 在主线程就设置了，直接获取
        log.info("{} 线程中ThreadLocal获取的值为: {}", Thread.currentThread().getName(), threadLocal.get());
    }
}
