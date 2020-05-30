package study.wyy.concurrency.thread.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-10 21:40
 * @description：入门
 * @modified By：
 * @version: $
 */

@Slf4j
public class StarterTest {
    /**
     * 尝试一边读数据库，一边写文件
     */
    @Test
    public void test() {
        // 发现并没有同时去做
        readFromDataBase();
        writeDataToFile();
    }

    public static void main(String[] args) {
        new Thread("read-thread"){
            @Override
            public void run() {
                readFromDataBase();
            }
        }.start();

        new Thread("write-thread"){
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();
    }



    public static void readFromDataBase() {
        // read data from database and handle it
        try {
            log.info("Begin read data from db");
            Thread.sleep(1000 * 30L);
            log.info("Read data done and start handle it");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void writeDataToFile(){

        try {
            log.info("Begin write data to file");
            Thread.sleep(1000 * 30L);
            log.info("Write data done and start handle it");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
