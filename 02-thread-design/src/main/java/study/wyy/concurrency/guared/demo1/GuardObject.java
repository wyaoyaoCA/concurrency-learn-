package study.wyy.concurrency.guared.demo1;


import java.util.concurrent.TimeoutException;

/**
 * @author by wyaoyao
 * @Description
 * @Date 2021/1/19 9:03 下午
 */
public class GuardObject<T> {

    private T result;

    /*****
     * 获取结果
     * @return
     */
    public T get() {
        synchronized (this) {
            while (null == result) {
                // 没有结果时候，就等待
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }


    public T get(long timeoutSeconds) throws TimeoutException {
        synchronized (this) {
            // 开始时间
            long beginTime = System.currentTimeMillis();
            // 经历的时间
            long passedTime = 0;
            long waitTime = timeoutSeconds * 1000 - passedTime;
            while (null == result) {
                if (waitTime <= 0) {
                    // 经历的时间已经大于我们的超时时间,就跳出循环
                    throw new TimeoutException();
                }
                try {
                    // 没有结果时候，就等待
                    // 这里等到
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 计算经历的时间
                passedTime = System.currentTimeMillis() - beginTime;
            }
            return result;
        }
    }

    /*****
     * 提交结果
     * @return
     */
    public void submit(T result) {
        synchronized (this) {
            this.result = result;
            // 唤醒等待结果的线程，来获取结果
            this.notifyAll();
        }
    }
}
