package study.wyy.concurrency.observer.simple;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-26 20:47
 * 八进制
 */
public class OctalObserver extends Observer {


    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}
