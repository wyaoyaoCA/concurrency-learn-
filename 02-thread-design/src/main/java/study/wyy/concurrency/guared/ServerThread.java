package study.wyy.concurrency.guared;

import java.util.Objects;
import java.util.Random;

public class ServerThread extends Thread{

    private final RequestQueue queue;

    private final Random random;

    /****
     * 标记：是否关掉
     */
    private volatile Boolean closed = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!closed){
            Request request = queue.getRequest();
            if(Objects.isNull(request)){
                // 忽略掉这个为null，这里不能break，那就跳出循环，服务就停了
                continue;
            }
            System.out.println("server -> request " + request.getValue());
            try {
                // 模拟处理请求的耗时
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                //e.printStackTrace();
                // 这里收到中断信号（调用了closed），就return，关闭服务
                return;
            }
        }
    }


    /**
    * @Description 关闭服务
    * @Author  wyaoyao
    * @Date   2020/9/10 9:57 下午
    * @Param
    * @Return
    * @Exception
    */
    public void closed(){
        closed = true;
        // 这里要打断，因为在get请求的时候可能因为队列中没有请求而wait，是无法判断到这个标记的
        this.interrupt();
        System.out.println("server closed");
    }
}
