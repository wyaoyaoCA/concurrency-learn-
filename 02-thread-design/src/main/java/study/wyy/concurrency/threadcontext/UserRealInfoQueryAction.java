package study.wyy.concurrency.threadcontext;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author by wyaoyao
 * @Description 用户真实信息查询action
 * @Date 2020/9/12 8:11 下午
 */
@Slf4j
public class UserRealInfoQueryAction implements UserAction{
    @Override
    public void execute(Context context) {
        log.info("start to query  user real info ");
        // 模拟对接公安系统，直接把request赋给real info
        UserRequest userRequest = context.getUserRequest();
        UserRealInfo userRealInfo = new UserRealInfo(
                userRequest.getName(),
                userRequest.getIdCard(),
                userRequest.getAddress()

        );
        context.setUserRealInfo(userRealInfo);
        log.info("start to query  user real info ");
    }
}
