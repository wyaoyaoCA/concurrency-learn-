package study.wyy.concurrency.threadcontext;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author by wyaoyao
 * @Description 用户真实信息校验action
 * @Date 2020/9/12 8:11 下午
 */
@Slf4j
public class UserRealInfoCheckAction implements UserAction {
    @Override
    public void execute(Context context) {
        UserRequest userRequest = context.getUserRequest();
        UserRealInfo userRealInfo = context.getUserRealInfo();
        log.info("start check user real info userRequest");
        if (Objects.isNull(userRequest)) {
            log.error("can not check real info cause by user request is null");
            throw new RuntimeException("user.request.is.null");
        }
        if (Objects.isNull(userRealInfo)) {
            log.error("can not check real info cause by user real info is null");
            throw new RuntimeException("user.real.info.is.null");
        }
        if (Objects.isNull(userRequest.getName()) ||
                Objects.isNull(userRealInfo.getName()) ||
                !userRequest.getName().equals(userRealInfo.getName())) {
            log.error(" check real info fail cause by user real name is not check pass; request name is {}, real name is {}", userRequest.getName(), userRealInfo.getName());
            throw new RuntimeException("user.real.name.not.check.pass");
        }

        if (Objects.isNull(userRequest.getIdCard()) ||
                Objects.isNull(userRealInfo.getIdCard()) ||
                !userRequest.getIdCard().equals(userRealInfo.getIdCard())) {
            log.error(" check real info fail cause by user idCard is not check pass; request idCard is {}, real idCard is {}", userRequest.getIdCard(), userRealInfo.getIdCard());
            throw new RuntimeException("user.idCard.not.check.pass");
        }
        if (Objects.isNull(userRequest.getAddress()) ||
                Objects.isNull(userRealInfo.getAddress()) ||
                !userRequest.getAddress().equals(userRealInfo.getAddress())) {
            log.error(" check real info fail cause by user address is not check pass; request address is {}, real address is {}", userRequest.getIdCard(), userRealInfo.getIdCard());
            throw new RuntimeException("user.address.not.check.pass");
        }
        log.info("check user real info success");

    }

}
