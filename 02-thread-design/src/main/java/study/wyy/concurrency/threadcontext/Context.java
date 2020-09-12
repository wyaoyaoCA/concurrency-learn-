package study.wyy.concurrency.threadcontext;

import lombok.Data;

/**
 * @author by wyaoyao
 * @Description 上下文
 * @Date 2020/9/12 7:55 下午
 */
@Data
public class  Context {

    private UserRequest userRequest;

    private UserRealInfo userRealInfo;
}
