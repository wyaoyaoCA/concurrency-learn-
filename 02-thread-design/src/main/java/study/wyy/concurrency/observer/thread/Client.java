package study.wyy.concurrency.observer.thread;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-26 21:52
 */
public class Client {

    public static void main(String[] args) {
        concurrentStart(Arrays.asList("1","2"),new ThreadLifeCycleListener());
    }

    /**
     * (
     * 测试方法，穿一组id，启动几个线程
     *
     * @param ids
     */
    public static void concurrentStart(List<String> ids, ThreadLifeCycleListener lifeCycleListener) {
        if (ids == null || ids.isEmpty()) {
            // 容错处理
            return;
        }
        ids.forEach(id -> new Thread(new ObserverRunnable(lifeCycleListener) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableStatus.RUNNING,Thread.currentThread(),null));
                    System.out.println("The Runnable " + Thread.currentThread().getName() + " process something");
                    // 模拟出现异常
                    if(Thread.currentThread().getName().equals("1")){
                        int a = 1/0;
                    }
                    notifyChange(new RunnableEvent(RunnableStatus.DONE,Thread.currentThread(),null));
                } catch (Exception e) {
                    System.out.println("The Runnable " + Thread.currentThread().getName() + " process fail");
                    notifyChange(new RunnableEvent(RunnableStatus.ERROR,Thread.currentThread(),e));
                }
            }
        }, id).start());
    }
}

