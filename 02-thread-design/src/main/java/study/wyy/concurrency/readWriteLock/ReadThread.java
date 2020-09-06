package study.wyy.concurrency.readWriteLock;

/**
 * @Description : 数据写入线程
 * @Author  wyaoyao
 * @Date   2020/9/6 6:05 下午
 * @Param
 * @Return
 * @Exception
 */
public class ReadThread extends Thread{

    private final ShareData shareData;

    public ReadThread(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        try{
            while (true){
                char[] data = shareData.read();
                System.out.println(Thread.currentThread().getName() + " read " + String.valueOf(data));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
