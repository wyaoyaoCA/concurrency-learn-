package study.wyy.concurrency.observer.simple;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-26 20:49
 */
public class Client {

    public static void main(String[] args) {
        final Subject subject = new Subject();
        subject.setState(10);
        Observer binaryObserver = new BinaryObserver(subject);
        Observer observerObserver = new OctalObserver(subject);
       // "subject happen change ....."
        subject.setState(8);

    }
}
