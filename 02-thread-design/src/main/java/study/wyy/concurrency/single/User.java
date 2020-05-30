package study.wyy.concurrency.single;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-27 21:09
 * 人：相当于使用共享资源--》线程
 */
@Slf4j
public class User extends Thread {
    private final String name;
    private final String address;
    private final Gate gate;

    public User(String name, String address, Gate gate) {
        this.name = name;
        this.address = address;
        this.gate = gate;
    }

    // 重写run
    @Override
    public void run() {
        log.info("{} Begin", name);
        // 不断的通过这个门
        while (true){
            this.gate.pass(this.name,this.address);
        }
    }
}
