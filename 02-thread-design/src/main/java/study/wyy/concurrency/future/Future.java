package study.wyy.concurrency.future;


/**
* @Description 封装返回给调用的的结果， 泛型T就是结果
*
* @Author  wyaoyao
* @Date   2020/9/6 10:14 下午
* @Param
* @Return
* @Exception
 *
*/
public interface Future<T>{

    /**
     *  @author: wyaoyao
     *  @Date: 2020/9/6 10:32 下午
     *  @Description: 提供一个方法，让调用方通过这个来拿调用结果
     */
    T get() throws InterruptedException;

    /**
     *  @author: wyaoyao
     *  @Date: 2020/9/6 10:34 下午
     *  @Description: 当任务执行完毕，将结果通知Future
     */
    void done(T result);
}
