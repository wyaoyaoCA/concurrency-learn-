package study.wyy.concurrency.guared.demo1;


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
    public T get(){
        synchronized (this){
            while (null == result){
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

    /*****
     * 提交结果
     * @return
     */
    public void submit(T result){
        synchronized (this){
            this.result = result;
            // 唤醒等待结果的线程，来获取结果
            this.notifyAll();
        }
    }
}
