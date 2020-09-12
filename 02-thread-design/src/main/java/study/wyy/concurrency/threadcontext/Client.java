package study.wyy.concurrency.threadcontext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by wyaoyao
 * @Description
 * @Date 2020/9/12 8:33 下午
 */
public class Client {
    public static void main(String[] args) {
        List<UserRequest> requests = initRequest(5);
        requests.forEach(request -> {
            new Thread(new ExecuteThread(request), request.getUserName() + "_Thread").start();
        });

    }

    private static List<UserRequest> initRequest(int size) {
        List list = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            UserRequest request = new UserRequest("name" + i, "username" + i, "idCard" + 1, "address" + i);
            list.add(request);
        }
        return list;
    }
}
