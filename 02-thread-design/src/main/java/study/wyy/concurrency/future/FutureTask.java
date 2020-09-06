package study.wyy.concurrency.future;


/**
* @Description: 任务，封装真正的执行的逻辑
* @Author  wyaoyao
* @Date   2020/9/6 10:16 下午
* @Param    
* @Return      
* @Exception   
*/
public interface FutureTask<T> {


    /**
    * @Description 封装真正的执行的逻辑
    * @Author  wyaoyao
    * @Date   2020/9/6 10:16 下午
    * @Param
    * @Return
    * @Exception
    */
    T call();
}
