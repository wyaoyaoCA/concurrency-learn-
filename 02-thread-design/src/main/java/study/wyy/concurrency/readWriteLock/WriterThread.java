package study.wyy.concurrency.readWriteLock;


import java.util.Random;

/**
* @Description : 数据读取线程
* @Author  wyaoyao
* @Date   2020/9/6 6:05 下午
* @Param
* @Return
* @Exception
*/
public class WriterThread extends Thread{

    private static final Random random = new Random(System.currentTimeMillis());

    private final ShareData shareData;

    // 写入内容
    private final String content;

    private int index = 0;

    public WriterThread(ShareData shareData, String content) {
        this.shareData = shareData;
        this.content = content;
    }

    /**
     *  @author: wyaoyao
     *  @Date: 2020/9/6 6:45 下午
     *  @Description:
     *   这么写有问题
     */
//    @Override
//    public void run() {
//        while (true){
//            char c = nextChar();
//            try {
//                shareData.write(c);
//                // 如果在里中断，下次醒过来还可能从这里进行
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void run() {
        // 在这里，如果中断，就会进入到catch，不用自己break，
        // 因为run方法已经结束了
        try{
            while (true){
                char c = nextChar();
                shareData.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     *  @author: wyaoyao
     *  @Date: 2020/9/6 6:41 下午
     *  @Description: 获取content中的下一个字符
     */
    private char nextChar(){
        char c = content.charAt(index);
        index++;
        if(index>=content.length()){
            // 如果index大于content的长度，就重置一下
            index = 0;
        }
        return c;
    }
}
