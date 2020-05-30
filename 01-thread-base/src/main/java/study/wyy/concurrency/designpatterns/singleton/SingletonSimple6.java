package study.wyy.concurrency.designpatterns.singleton;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-06 14:41
 *
 */
public class SingletonSimple6 {


    private SingletonSimple6(){
    }

    private enum Singleton {
        INSTANCE;
        private final SingletonSimple6 instance;

        Singleton(){
            instance = new SingletonSimple6();
        }
        public SingletonSimple6 getInstance(){
            return instance;
        }
    }

    public static SingletonSimple6 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
