package study.wyy.concurrency.future;


/**
* @Description 异步的实现
* @Author  wyaoyao
* @Date   2020/9/6 10:20 下午
* @Param
* @Return
* @Exception
*/
public class AsyncFuture<T> implements Future<T>{

    /**
     *  @Description: 是否已经执行完
     */
    private volatile  boolean isDone = false;

    private T result;
    @Override
    public T get() throws InterruptedException {
        synchronized (this){
            while (!isDone){
                // 如果还没有完成，外部（调用方）想通过这个方法拿执行结果，肯定是不能让拿的，所以等待
                this.wait();
            }
        }
        // 完成的话，就返回结果
        return result;
    }

    /**
     *  @author: wyaoyao
     *  @Date: 2020/9/6 10:34 下午
     *  @Description: 当任务执行完毕，将结果通知Future
     */
    @Override
    public void done(T result){
        synchronized (this){
            this.result = result;
            this.isDone = true;
            // 唤醒线程
            this.notifyAll();
        }
    }
}
