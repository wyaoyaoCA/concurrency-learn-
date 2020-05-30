package study.wyy.concurrency.observer.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-26 20:30
 * 主题：被观察者
 */
public class Subject {

    // 定义观察者列表
    private List<Observer> observers = new ArrayList<>();

    private int state;

    // 追加一个观察者
    public void attach(Observer observer){
        observers.add(observer);
    }


    public int getState(){
        return state;
    }

    public void setState(int state){
        if(state == this.state){
            return;
        }
        this.state = state;
        // 发生了变化，就通知所有的观察者
        notifyAllObserver();
    }


    // 通知所有的观察者
    private void notifyAllObserver(){
        observers.forEach(Observer::update);
    }
}
