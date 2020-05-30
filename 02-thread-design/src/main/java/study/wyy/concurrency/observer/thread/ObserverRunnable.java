package study.wyy.concurrency.observer.thread;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-26 21:39
 * 定义一个可被观察的runnable
 */
public abstract class ObserverRunnable implements Runnable {

    // 定义一个Listener，监听线程的声明周期(观察者)
    final protected LifeCycleListener listener;

    // 通过构造传入Listener
    public ObserverRunnable(final LifeCycleListener listener) {
        this.listener = listener;
    }

    // 通知
    protected void notifyChange(RunnableEvent event) {
        listener.onEvent(event);
    }

    public enum RunnableStatus {
        RUNNING, ERROR, DONE
    }

    // 定义一个事件
    public static class RunnableEvent {
        // 任务的状态
        private final RunnableStatus status;
        // 哪个线程呢
        private final Thread thread;
        // 异常
        private final Throwable cause;

        public RunnableEvent(RunnableStatus status, Thread thread, Throwable cause) {
            this.status = status;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableStatus getStatus() {
            return status;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }


}
