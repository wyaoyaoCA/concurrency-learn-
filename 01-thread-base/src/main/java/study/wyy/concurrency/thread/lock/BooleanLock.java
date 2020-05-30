package study.wyy.concurrency.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author ：wyy
 * @date ：Created in 2020-04-03 20:21
 * @description：
 * @modified By：
 * @version: $
 */
@Slf4j
public class BooleanLock implements Lock {


    private Collection<Thread> blockThread = new ArrayList<>();
    /**
     * 默认值为True，尚未有其他线程持有该锁，能加锁，
     * 否则：该锁已经已经其他线程持有，不能加锁
     */
    private Boolean enableLock = Boolean.TRUE;

    private Thread currentThread;

    @Override
    public synchronized void lock() throws InterruptedException {
        while (!enableLock) {
            // 表示锁已经被其他线程拿走，进入等待,
            blockThread.add(Thread.currentThread());
            this.wait();

        }
        // 表示锁已经被释放，可以获取锁
        // 当前线程拿到锁可以执行，从blockThread移除
        blockThread.remove(Thread.currentThread());
        // enableLock set false，其他线程不可再持有该锁
        this.enableLock = Boolean.FALSE;
        // 记录抢到锁的线程
        currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0) {
            log.error("mills => [{}] can not <= 0", mills);
            throw new IllegalArgumentException();
        }
        long hasRemaining = mills;
        // 当前线程，抢锁结束的时间
        long endTime = System.currentTimeMillis() + hasRemaining;
        while (!enableLock) {
            if (hasRemaining <= 0) {
                // 说明已经超时
                throw new TimeOutException("Time out");
            }
            // 表示锁已经被其他线程拿走，进入等待,
            this.wait(mills);
            // 当这个线程被唤醒的时候，计算一下是否超时， hasRemaining <=0 说明超时
            hasRemaining = endTime - System.currentTimeMillis();
        }
        // 表示锁已经被释放，可以获取锁
        // 当前线程拿到锁可以执行，从blockThread移除
        blockThread.remove(Thread.currentThread());
        // enableLock set false，其他线程不可再持有该锁
        this.enableLock = Boolean.FALSE;
        // 记录抢到锁的线程
        currentThread = Thread.currentThread();

    }

    @Override
    public synchronized void unLock() {
        // 判断是不是抢到锁的线程，只有是持有该锁的线程才可以释放该锁
        if (currentThread != null && currentThread == Thread.currentThread()) {
            // enableLock set true, 当前线程释放了锁，其他线程可进行抢夺该锁
            this.enableLock = Boolean.TRUE;
            log.info("{} release lock", Thread.currentThread().getName());
            // 别忘记唤起其他线程，进入runnable状态
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockThread() {
        // 设置返回的当前被锁住的线程是不能修改的，防止调用者修改该集合
        return Collections.unmodifiableCollection(blockThread);
    }

    @Override
    public int getBlockSize() {
        return blockThread.size();
    }
}
