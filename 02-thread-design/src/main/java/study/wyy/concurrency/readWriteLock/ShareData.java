package study.wyy.concurrency.readWriteLock;

/**
 * @Description: 共享数据
 * @Author wyaoyao
 * @Date 2020/9/6 5:22 下午
 * @Param
 * @Return
 * @Exception
 */
public class ShareData {
    // 定义一个buffer，从里面读取数据和写入数据
    private final char[] buffer;

    // 定义一个锁
    private final ReadWriteLock lock = new ReadWriteLock();

    public ShareData(int size) {
        this.buffer = new char[size];
        // 初始化数据
        for (int i = 0; i < size; i++) {
            this.buffer[i] = '*';
        }
    }

    /**
     *  @author: wyaoyao
     *  @Date: 2020/9/6 5:27 下午
     *  @Description: 读取数据的方法
     */
    public  char[] read() throws InterruptedException {
        try {
            // 1 加读锁
            lock.readLock();
            // 2 读取数据
            return doRead();
        }finally {
            // 释放锁
            lock.readUnLock();
        }
    }

    private char[] doRead() {
        char[] temp = new char[buffer.length];
        for (int i =0; i<buffer.length;i++){
            temp[i] = buffer[i];
        }
        // 读取结束之后，休眠一会
        sleep(50);
        return temp;
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            // 1 加写锁
            lock.writeLock();
            // 2 写数据
            doWrite(c);
        }finally {
            // 释放锁
            lock.unWriteLock();
        }
    }

    private void doWrite(char c) {
        for (int i =0; i<buffer.length;i++){
            buffer[i] = c;
            sleep(10);
        }
    }
}
