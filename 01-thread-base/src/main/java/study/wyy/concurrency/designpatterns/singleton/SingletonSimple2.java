package study.wyy.concurrency.designpatterns.singleton;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-06 14:41
 * 饿汉式：懒加载
 */
public class SingletonSimple2 {
    private static SingletonSimple2 instance;

    private SingletonSimple2(){

    }

    // 存在线程安全问题
/*

    public static SingletonSimple2 getInstance(){
        if(null == instance){
            instance = new SingletonSimple2();
        }
        return instance;
    }
*/


    // 加锁
    public synchronized static SingletonSimple2 getInstance(){
        if(null == instance){
            instance = new SingletonSimple2();
        }
        return instance;
    }

}
