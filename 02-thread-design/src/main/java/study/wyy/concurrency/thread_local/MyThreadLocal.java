package study.wyy.concurrency.thread_local;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *  @author: wyaoyao
 *  @Date: 2020/9/12 5:59 下午
 *  @Description: 简单模拟一个ThreadLocal
 */
public class MyThreadLocal<T> {

    private final Map<Thread,T> storage= new HashMap();

    public void set(T t){
        synchronized (this){
            Thread key = Thread.currentThread();
            storage.put(key,t);
        }
    }

    public T get(){
        synchronized (this){
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if(Objects.isNull(value)){
                return  initValue();
            }
            return value;
        }
    }

    public T initValue() {
        return null;
    }
}
