package study.wyy.concurrency.guared;


import java.util.LinkedList;

/**
 *  @author: wyaoyao
 *  @Date: 2020/9/10 9:48 下午
 *  @Description: 请求队列
 */
public class RequestQueue {

    LinkedList<Request> queue = new LinkedList<>();

    /**
     *  @author: wyaoyao
     *  @Date: 2020/9/10 9:41 下午
     *  @Description: 服务端端通过该方法向队列中get请求
     */
    public Request getRequest(){
        synchronized (queue){
            while (queue.size()<=0){
                // 请求队列中没有请求，那就等着
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    // 当被唤醒的时候，需要break出去，进行工作，比如客户端请求来临的时候
                    return null;
                }
            }
            // 请求队列中有请求数据，那就返回出去
            return queue.removeFirst();
        }
    }

    /**
     *  @author: wyaoyao
     *  @Date: 2020/9/10 9:41 下午
     *  @Description: 客户端通过该方法向队列中提交请求
     *
     */
    public void putRequest(Request request){
        synchronized (queue){
           queue.addLast(request);
           // 唤醒其他线程 起来工作，在get的时候，可能没有请求数据，给wait了，一旦放入请求数据，自然就要唤醒
            queue.notifyAll();
        }
    }

}
