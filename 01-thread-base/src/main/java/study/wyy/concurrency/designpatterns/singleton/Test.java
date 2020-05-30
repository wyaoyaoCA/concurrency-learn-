package study.wyy.concurrency.designpatterns.singleton;

import org.junit.Assert;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-06 14:39
 */
public class Test {

    // 测试
    @org.junit.Test
    public void test() {
        SingletonSimple1 instance1 = SingletonSimple1.getInstance();
        SingletonSimple1 instance2 = SingletonSimple1.getInstance();
        Assert.assertTrue(instance1 == instance2);

    }

    /**
     * 不安全，并不是单例的
     * study.wyy.concurrency.designpatterns.singleton.SingletonSimple2@608f35ee
     * study.wyy.concurrency.designpatterns.singleton.SingletonSimple2@5617e729
     * study.wyy.concurrency.designpatterns.singleton.SingletonSimple2@7df5ed4e
     * study.wyy.concurrency.designpatterns.singleton.SingletonSimple2@7df5ed4e
     * study.wyy.concurrency.designpatterns.singleton.SingletonSimple2@7df5ed4e
     */

    // 测试
    @org.junit.Test
    public void test2() {
        for (int i = 0; i<100; i++){
            new Thread(()->{
                SingletonSimple2 instance = SingletonSimple2.getInstance();
                System.out.println(instance);

            },"T"+i).start();
        }


    }

    // 测试
    @org.junit.Test
    public void test3() {
        for (int i = 0; i<100; i++){
            new Thread(()->{
                SingletonSimple3 instance = SingletonSimple3.getInstance();
                System.out.println(instance);

            },"T"+i).start();
        }
    }

    // 测试
    @org.junit.Test
    public void test4() {
        for (int i = 0; i<100; i++){
            new Thread(()->{
                SingletonSimple6 instance = SingletonSimple6.getInstance();
                System.out.println(instance);

            },"T"+i).start();
        }
    }
}
