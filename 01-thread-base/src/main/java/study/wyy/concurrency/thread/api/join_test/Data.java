package study.wyy.concurrency.thread.api.join_test;

/**
 * @author ：wyy
 * @date ：Created in 2020-03-28 11:08
 * @description：数据模型
 * @modified By：
 * @version: $
 */
public class Data {
    private String data;

    public Data(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data='" + data + '\'' +
                '}';
    }
}
