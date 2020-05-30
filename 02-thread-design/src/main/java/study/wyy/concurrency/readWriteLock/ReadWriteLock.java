package study.wyy.concurrency.readWriteLock;

/**
 * @author ：wyaoyao
 * @date ： 2020-05-06 21:28
 */
public class ReadWriteLock {

    // 记录当前有多少个线程在进行读取操作
    private int readingReaders = 0;

    // 记录当前有多少个线程想要读取，但是读取不了，放到了wait队列中
    private int waitingReaders = 0;

    // 记录当前有多少个线程在进行写操作，只有一个
    private int writingWriters = 0;

    // 记录当前有多少个线程想要写入，但是写入不了，放到了wait队列中，在等待其他释放锁
    private int waitingWriters = 0;

    /**
     * 加读锁
     */
    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders++;
        try {
            while (this.writingWriters > 0) {
                // this.writingWriters > 0 说明有线程正在进行写操作，不能进行读取
                // 只能等待
                this.wait();
            }
            // 说明没有线程进行写入，可以进行读取操作
            this.readingReaders++;
        } finally {
            // 最终释放锁的时候，要减减waitingReaders
            this.waitingReaders--;
        }

    }

    /**
     * 释放读锁
     */
    public synchronized void unReadLock() {
        // readingReaders 减一
        this.readingReaders--;
        // 唤醒其他等待的线程
        this.notifyAll();
    }

    /**
     * 加写锁
     */
    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while (this.readingReaders > 0 || this.writingWriters > 0) {
                //this.readingReaders > 0 || this.writingWriters > 0: 当有其他线程在进行读取或者写入的时候，
                // 则当前线程不能进行写操作，只能等待
                this.wait();
            }
        }finally {
            // 最终释放锁的时候，要减减waitingReaders
            this.waitingWriters--;
        }

    }

    /**
     * 释放锁
     */
    public synchronized void unWriteLock(){
        this.waitingWriters--;
        this.notifyAll();
    }


}
