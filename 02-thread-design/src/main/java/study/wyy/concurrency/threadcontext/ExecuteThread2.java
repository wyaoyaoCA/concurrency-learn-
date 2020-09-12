package study.wyy.concurrency.threadcontext;

/**
 * @author by wyaoyao
 * @Description
 * @Date 2020/9/12 8:33 下午
 */
public class ExecuteThread2 implements Runnable {
    private final UserAction userRequestCheckAction = new UserRequestCheckAction();
    private final UserAction userRealInfoQueryAction = new UserRealInfoQueryAction();
    private final UserAction userRealInfoCheckAction = new UserRealInfoCheckAction();
    private final UserAction userInfoSaveAction = new UserInfoSaveAction();
    private final UserRequest userRequest;

    public ExecuteThread2(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    @Override
    public void run() {
        ActionContext.getActionContext().getContext().setUserRequest(userRequest);
        userRequestCheckAction.execute();
        userRealInfoQueryAction.execute();
        userRealInfoCheckAction.execute();
        userInfoSaveAction.execute();
    }
}
