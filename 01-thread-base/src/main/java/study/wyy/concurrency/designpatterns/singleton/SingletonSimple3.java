package study.wyy.concurrency.designpatterns.singleton;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-06 14:41
 * 饿汉式：懒加载
 */
public class SingletonSimple3 {
    private static SingletonSimple3 instance;

    private SingletonSimple3(){

    }

    public static SingletonSimple3 getInstance(){
        if(null == instance){
            synchronized (SingletonSimple3.class){
                if(null == instance){
                    instance = new SingletonSimple3();
                }
            }
        }
        return SingletonSimple3.instance;
    }

}
