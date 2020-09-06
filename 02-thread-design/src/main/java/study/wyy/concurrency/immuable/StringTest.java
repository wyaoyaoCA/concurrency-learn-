package study.wyy.concurrency.immuable;

public class StringTest {

    public static void main(String[] args) {
        String s1 = "Hello";
        // 这里是改变了吗
        // System.out.println(s1.replace("l","k"));
        String s2 = s1.replace("l", "k");
        // 其实是返回了一个新的对象
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println(s1==s2);

    }
}
