package study.wyy.concurrency.observer.simple;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-26 20:43
 * 二进制
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}
