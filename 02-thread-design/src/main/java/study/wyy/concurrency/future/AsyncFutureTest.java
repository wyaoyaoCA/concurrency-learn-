package study.wyy.concurrency.future;

public class AsyncFutureTest {

    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            // 模拟方法执行的耗时
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINSH";
        });
        // 干其他事情去了
        doOther();
        // 其他事情做完了，我来取刚刚的结果（蛋糕）
        String result = future.get();
        System.out.println(result);


    }

    private static void doOther() throws InterruptedException {
        System.out.println("do other thing....");
        // 模拟方法执行的耗时
        Thread.sleep(1000L);
    }
}
