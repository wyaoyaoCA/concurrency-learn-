package study.wyy.concurrency.thread.api.close;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 20:40
 * @description： 对外暴露线程
 * @modified By：
 * @version: $
 */
@Slf4j
public class ThreadService {

    // 父线程，执行线程，由它来开启子线程去执行任务
    private Thread excuteThread;

    // 任务是否执行结束
    private boolean hasFinshed = false;
    /**
     * 执行任务
     * @param task 要执行的任务单元
     */
    public void excute(Runnable task){
        excuteThread = new Thread(){
            @Override
            public void run() {
                // 开启一个子线程，去执行我们任务单元
                Thread runner = new Thread(task);
                // 设置为守护线程
                runner.setDaemon(Boolean.TRUE);
                // 开启子线成
                runner.start();
                // 这里要让excuteThread join住（阻塞住），等待这个子线程的执行，excuteThread的声明周期就会很长，交给我们自己控制这父线程的声明周期
                // 如果不join住，excuteThread的生命周期也就到这里为止了，会导致子线程还没执行或者刚执行就因为父线程的结束而结束了
                try {
                    runner.join();
                    // 父线程excuteThread，join结束了，也就是runner线程任务已经执行完成，将hasFinshed改为true
                    // 并且父线程也就到此执行就结束了，自然子线程runner作为守护线程也就随之结束
                    hasFinshed = true;
                } catch (InterruptedException e) {
                    // 这里捕获到打断信号，excuteThread的join也就结束了（结束阻塞），excuteThread就会很快运行结束
                    // excuteThread父线程结束，子线程作为守护线程也会结束，就实现了任务的的中断
                    log.info("excuteThread has interrupt");
                }
            }
        };
        excuteThread.start();
    }

    /**
     * 显式的杀死excuteThread这个执行线程
     * @param mills 任务的超时时间，任务最大执行时间
     */
    public void shutdown(long mills){
        long currentTime = System.currentTimeMillis();
        // 判断任务是否执行结束，在超时时间内如果结束了，就不能等到超时时间到了才关闭，浪费资源
        while (!hasFinshed){
            // 是任务没有执行结束，判断是否超时
            if(System.currentTimeMillis() -currentTime >= mills ){
                // 执行超时
                log.info("任务执行超时，需要结束");
                // 打断执行线程, 就会在执行线程阻塞的地方捕捉到InterruptedException => 上面的join的时候就会阻塞住
                excuteThread.interrupt();
                break;
            }

            // 任务没有结束，也没有超时，简单休眠一下
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
