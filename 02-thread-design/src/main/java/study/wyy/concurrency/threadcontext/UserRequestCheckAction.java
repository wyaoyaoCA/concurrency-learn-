package study.wyy.concurrency.threadcontext;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author by wyaoyao
 * @Description 用户请求参数校验action
 * @Date 2020/9/12 8:11 下午
 */
@Slf4j
public class UserRequestCheckAction implements UserAction{
    @Override
    public void execute(Context context) {
        UserRequest userRequest = context.getUserRequest();
        log.info("start to check  user request");
        if(Objects.isNull(userRequest)){
            log.error("user request is null");
            throw new RuntimeException("user request is null");
        }
        if(Objects.isNull(userRequest.getAddress())){
            log.error("user request address is null");
            throw new RuntimeException("user request is null");
        }
        if(Objects.isNull(userRequest.getIdCard())){
            log.error("user request idCard is null");
            throw new RuntimeException("user request is null");
        }
        if(Objects.isNull(userRequest.getUserName())){
            log.error("user request username is null");
            throw new RuntimeException("user request is null");
        }
        if(Objects.isNull(userRequest.getName())){
            log.error("user request is null");
            throw new RuntimeException("user request real name is null");
        }
        log.info("check  user request success ");

    }

    @Override
    public void execute() {
        Context context = ActionContext.getActionContext().getContext();
        execute(context);
    }
}
