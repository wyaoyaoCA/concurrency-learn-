package study.wyy.concurrency.designpatterns.singleton;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-06 14:32
 * 单例模式：饿汉式
 * 线程安全
 * 但是在不使用的时候占用内存，因为通过static是类主动加载，不能能够懒加载
 */
public class SingletonSimple1 {

    private static final SingletonSimple1 instance = new SingletonSimple1();

    // 构造私有化。不允许外部调用
    private SingletonSimple1() {
    }

    public static SingletonSimple1 getInstance() {
        return instance;
    }




}
