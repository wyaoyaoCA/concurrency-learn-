package study.wyy.concurrency.threadcontext;

/**
 * @author by wyaoyao
 * @Description
 * @Date 2020/9/12 8:33 下午
 */
public class ExecuteThread implements Runnable {
    private final UserAction userRequestCheckAction = new UserRequestCheckAction();
    private final UserAction userRealInfoQueryAction = new UserRealInfoQueryAction();
    private final UserAction userRealInfoCheckAction = new UserRealInfoCheckAction();
    private final UserAction userInfoSaveAction = new UserInfoSaveAction();
    private final UserRequest userRequest;

    public ExecuteThread(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    @Override
    public void run() {
        Context context = new Context();
        context.setUserRequest(userRequest);
        userRequestCheckAction.execute(context);
        userRealInfoQueryAction.execute(context);
        userRealInfoCheckAction.execute(context);
        userInfoSaveAction.execute(context);
    }
}
