package study.wyy.concurrency.single;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-27 21:37
 * 测试
 */
public class Client {
    public static void main(String[] args) {
        // 创建一个gate
        Gate gate = new Gate();
        // 搞他三个
        User bj = new User("BaoBao","BeiJing",gate);
        User sh = new User("ShangLao","ShangHai",gate);
        User gz = new User("GuangLao","Guangzhou",gate);

        // 启动
        bj.start();
        sh.start();
        gz.start();

    }
}
