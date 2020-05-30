package study.wyy.concurrency.observer.thread;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-26 21:49
 * 定义一个实现类
 */
public class ThreadLifeCycleListener implements LifeCycleListener {

    // 启动可能不止一个线程，就会存在线程安全问题，搞个锁出来
    private final Object LOCK = new Object();


    @Override
    public void onEvent(ObserverRunnable.RunnableEvent event) {
        synchronized (LOCK){
            System.out.println("The Runnable  " + event.getThread().getName() + " status is " + event.getStatus());
            if(event.getCause() != null){
                event.getCause().printStackTrace();
            }
        }
    }
}
