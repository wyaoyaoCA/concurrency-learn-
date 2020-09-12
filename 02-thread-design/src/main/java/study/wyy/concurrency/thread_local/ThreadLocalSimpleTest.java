package study.wyy.concurrency.thread_local;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 *  @author: wyaoyao
 *  @Date: 2020/9/12 4:48 下午
 *  @Description: ThreadLocal 简单测试
 */
@Slf4j
public class ThreadLocalSimpleTest {

    private ThreadLocal<String> threadLocal = new ThreadLocal();


    @Test
    public void test1() {
        threadLocal.set("java");
        String value = threadLocal.get();
        log.info("当前取出的值为: {}",value);
    }


    /**
    * @Description 如果不设置的值，取出的是null
    * @Author  wyaoyao
    * @Date   2020/9/12 4:55 下午
    * @Param
    * @Return
    * @Exception
    */
    @Test
    public void test2() {
        String value = threadLocal.get();
        log.info("当前取出的值为: {}",value);
    }


    private ThreadLocal<String> threadLocal1 = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return "java";
        }
    };

    @Test
    public void test3() {
        String value = threadLocal1.get();
        log.info("当前取出的值为: {}",value);
    }



}
