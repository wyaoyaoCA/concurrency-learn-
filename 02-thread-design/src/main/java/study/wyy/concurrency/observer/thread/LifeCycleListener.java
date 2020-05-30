package study.wyy.concurrency.observer.thread;


public interface LifeCycleListener {

    void onEvent(ObserverRunnable.RunnableEvent event);
}
