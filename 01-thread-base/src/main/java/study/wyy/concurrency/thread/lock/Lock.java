package study.wyy.concurrency.thread.lock;

import java.util.Collection;

public interface Lock {


    /**
     * 加锁，这个和synchronized的区别就是：
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     *  加锁，指定允许等待时间，如果超时，还没有获得锁，则就抛出TimeOutException
     * @param mills
     * @throws InterruptedException
     * @throws TimeOutException
     */
    void lock(long mills) throws InterruptedException,TimeOutException;


    /**
     * 释放锁
     */
    void unLock();

    /**
     * 返回此时被该锁阻塞的线程
     * @return
     */
    Collection<Thread> getBlockThread();

    int getBlockSize();

    class TimeOutException extends Exception{
        public TimeOutException(String message) {
            super(message);
        }
    }
}
