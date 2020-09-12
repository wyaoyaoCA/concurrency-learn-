package study.wyy.concurrency.threadcontext;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author by wyaoyao
 * @Description 用户信息保存到db
 * @Date 2020/9/12 8:11 下午
 */
@Slf4j
public class UserInfoSaveAction implements UserAction{
    @Override
    public void execute(Context context) {
        // 模拟一把
        UserRequest userRequest = context.getUserRequest();
        log.info("start save user info to db userRequest : {}",userRequest);
        save(userRequest);
    }

    @Override
    public void execute() {
        Context context = ActionContext.getActionContext().getContext();
        execute(context);
    }


    private void save(UserRequest userRequest) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("save user info to db userRequest success");
    }
}
