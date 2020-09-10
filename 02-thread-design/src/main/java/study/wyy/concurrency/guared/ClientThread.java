package study.wyy.concurrency.guared;


import java.util.Random;

/**
 *  @author: wyaoyao
 *  @Date: 2020/9/10 9:47 下午
 *  @Description: 模拟客户端
 */
public class ClientThread extends Thread{
    private final RequestQueue queue;

    private final Random random;

    public ClientThread(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for(int i =0;i<10;i++){
            // 模拟put 请求 10次
            Request login_request = new Request("login request");
            System.out.println("client -> request " + login_request.getValue());
            queue.putRequest(login_request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
