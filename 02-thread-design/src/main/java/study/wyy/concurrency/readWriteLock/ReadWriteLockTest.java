package study.wyy.concurrency.readWriteLock;


/**
* @Description 测试
* @Author  wyaoyao
* @Date   2020/9/6 6:52 下午
* @Param
* @Return
* @Exception
*/
public class ReadWriteLockTest {

    public static void main(String[] args) {
        final ShareData shareData = new ShareData(10);
        new ReadThread(shareData).start();
        new ReadThread(shareData).start();
        new ReadThread(shareData).start();
        new ReadThread(shareData).start();
        new WriterThread(shareData,"qwertyuiop").start();
        new WriterThread(shareData,"QWERTYUIOP").start();
    }
}
