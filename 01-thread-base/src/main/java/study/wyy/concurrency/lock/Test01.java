package study.wyy.concurrency.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author by wyaoyao
 * @Description
 * @Date 2021/1/15 8:03 下午
 */
public class Test01 {

    public static void main(String[] args) throws InterruptedException {
        //test1();
        //test2();
        //test3();
    }


    public static void test3() throws InterruptedException {
        Object obj = new Object();
        new Thread(()->{
            // 解锁前打印一下对象头
            System.out.println("=========t1===========");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            System.out.println("=========t1===========");
            synchronized (obj){
                // 打印一下对象头
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            }
            System.out.println("===========t1=========");
            // 解锁后打印一下对象头
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            // 执行完，去唤醒一下t2
            synchronized (Test01.class){
                Test01.class.notifyAll();
            }
        },"t1").start();

        new Thread(()->{
            // 先让t2等待，等待t1执行完, 这里的Test01的class对象做锁，只是为了实现wait而已
            // 保证两个线程不会竞争
            synchronized (Test01.class){
                try {
                    Test01.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("=========t2===========");
            // 解锁前打印一下对象头
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            System.out.println("========t2============");
            synchronized (obj){
                // 打印一下对象头
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            }
            System.out.println("=============t2=======");
            // 解锁后打印一下对象头
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        },"t2").start();
    }

    public static void test2() throws InterruptedException {
        // 因为偏向锁是有延迟，这里就先sleep一下，也可以通过虚拟机参数关闭
        //TimeUnit.SECONDS.sleep(10);
        Object obj = new Object();
        // hashcode会撤销偏向锁
        obj.hashCode();
        // 解锁前打印一下对象头
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println("====================");
        synchronized (obj){
            // 打印一下对象头
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        System.out.println("====================");
        // 解锁后打印一下对象头
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    public static void test1() throws InterruptedException {
        Object obj1 = new Object();
        String s = ClassLayout.parseInstance(obj1).toPrintable();
        System.out.println(s);

        System.out.println("====================");
        TimeUnit.SECONDS.sleep(4);
        Object obj2 = new Object();
        String s1 = ClassLayout.parseInstance(obj2).toPrintable();
        System.out.println(s1);
    }



}
