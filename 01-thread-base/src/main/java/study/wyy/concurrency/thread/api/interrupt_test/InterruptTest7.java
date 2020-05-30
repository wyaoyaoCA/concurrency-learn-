package study.wyy.concurrency.thread.api.interrupt_test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 14:28
 * @description：中断线程
 * @modified By：
 * @version: $
 */
@Slf4j
public class InterruptTest7 {


    /**(=
     * 如果该线程处于阻塞状态（sleep，wait，join），此时打断该线程，则会捕捉到InterruptedException异常，它的中断状态将被清除
     */

    public static void main(String[] args)  {
        Thread main = Thread.currentThread();
       Thread t1 = new Thread(){
           @Override
           public void run() {
               while (true){

               }
           }
       };
        t1.start();

       Thread t2 = new Thread(){
           @Override
           public void run() {
               // 这里打断main
               main.interrupt();
           }
       };
       t2.start();
       // 进行join, 这里进行了join，主线程（父线程）就会被阻塞，直到子线程运行结束，
        // 所以只好在前面再开一个线程去打断
        try {
            t1.join();
        } catch (InterruptedException e) {
            log.info("捕捉到main的打断信号");
            e.printStackTrace();
        }
    }
}
