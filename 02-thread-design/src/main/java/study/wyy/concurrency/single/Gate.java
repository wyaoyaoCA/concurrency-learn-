package study.wyy.concurrency.single;

/**
 * @author ：wyaoyao
 * @date ： 2020-04-27 20:57
 *
 * 门：相当于共享资源
 */
public class Gate {

    // 记录通过的人数
    private int counter = 0;

    // 记录通过的人的name
    private String name = "Nobody";

    // 记录通过的人的address
    private String address = "NoAddress";


    public synchronized void pass(String name,String address){
        this.counter++;
        this.address = address;
        this.name = name;
        verify();
    }

    /**
     * 随便写的一个检查：name和address的首字母要一样
     */
    private void verify() {
        if(this.name.charAt(0) != this.address.charAt(0)){
            System.out.println("*******BROKEN********" + toString());
        }
    }

    @Override
    public synchronized String toString() {
        return "NO." + counter + ":" + name + "," + address;
    }
}
