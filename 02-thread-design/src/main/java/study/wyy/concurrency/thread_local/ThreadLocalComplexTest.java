package study.wyy.concurrency.thread_local;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 *  @author: wyaoyao
 *  @Date: 2020/9/12 4:48 下午
 *  @Description: ThreadLocal 复杂测试
 *  定义一个ThreadLocal，分别在三个线程去设置和获取数据
 */
@Slf4j
public class ThreadLocalComplexTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal();

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
