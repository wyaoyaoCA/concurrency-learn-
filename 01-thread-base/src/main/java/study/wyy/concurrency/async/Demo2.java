package study.wyy.concurrency.async;

/**
 * @author by wyaoyao
 * @Description
 * @Date 2021/1/13 9:33 下午
 */
public class Demo2 {

    static final Object obj = new Object();

    public static void method1() {
        synchronized (obj) {
            // 同步块 A
            method2();
        }
    }

    public static void method2() {
        synchronized (obj) {
            // 同步块 B
        }
    }
}
