package study.wyy.concurrency.observer.simple;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-26 20:34
 * 观察者：可以是个抽象类，也可以是个接口，jdk until包下提供了一个Obseerver接口，功能比较强大
 */
public abstract class Observer {

    public Observer(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    protected Subject subject;

    // 被观察者改变了，就会通知到update方法
    abstract void update();
}
