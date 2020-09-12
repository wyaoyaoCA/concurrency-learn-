package study.wyy.concurrency.threadcontext;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author by wyaoyao
 * @Description
 * @Date 2020/9/12 8:06 下午
 */
@Data
@AllArgsConstructor
public class UserRequest implements Serializable {

    /****
     * 真实姓名
     */
    private final String name;

    /****
     * 用户名
     */
    private final String userName;

    /****
     * 身份证号
     */
    private final String idCard;

    /****
     * 真实地址（籍贯）
     */
    private final String address;



}
