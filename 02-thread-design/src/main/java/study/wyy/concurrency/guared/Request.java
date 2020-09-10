package study.wyy.concurrency.guared;


/**
 *  @author: wyaoyao
 *  @Date: 2020/9/10 9:47 下午
 *  @Description: 请求
 */
public class Request {
    // 简单模拟请求参数中的属性
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Request(String value) {
        this.value = value;
    }
}
