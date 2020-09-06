package study.wyy.concurrency.future;

/**
* @Description 将Future和FutureTask桥接起来
* @Author  wyaoyao
* @Date   2020/9/6 10:18 下午
* @Param
* @Return
* @Exception
*/
public class FutureService {

    /**
     *  @author: wyaoyao
     *  @Date: 2020/9/6 10:18 下午
     *  @Description: 提交任务
     */
    public <T> Future<T> submit(final FutureTask<T> task){
        // new AsyncFuture，返回给调用方
        Future<T> future = new AsyncFuture<>();
        // 开启一个线程去执行本次的任务(异步)
        new Thread(()->{
            // 执行逻辑
            T result = task.call();
            // 执行完毕，通知给future
            future.done(result);

        }).start();
        return future;
    }
}
