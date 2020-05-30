package study.wyy.concurrency.designpatterns.singleton;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-06 14:41
 *
 */
public class SingletonSimple5 {


    private SingletonSimple5(){
    }

    private static class InstanceHolder {
        private final static SingletonSimple5 instance = new SingletonSimple5();
    }

    public static SingletonSimple5 getInstance(){
        return InstanceHolder.instance;
    }

}
