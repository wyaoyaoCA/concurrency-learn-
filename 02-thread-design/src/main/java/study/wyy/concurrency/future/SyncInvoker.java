package study.wyy.concurrency.future;

public class SyncInvoker {

    public static void main(String[] args) throws InterruptedException {
        String result = get();
        System.out.println(result);
        doOther();
    }

    private static void doOther() {
        System.out.println("do other thing....");
    }

    private static String get() throws InterruptedException {
        // 模拟get方法执行的耗时
        Thread.sleep(10000L);
        return "FINSH";
    }
}
