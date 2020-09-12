package study.wyy.concurrency.threadcontext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author by wyaoyao
 * @Description 用户的真实信息
 * @Date 2020/9/12 8:06 下午
 */
@Data
@AllArgsConstructor
public class UserRealInfo implements Serializable {

    /****
     * 真实姓名
     */
    private final String name;

    /****
     * 身份证号
     */
    private final String idCard;

    /****
     * 真实地址（籍贯）
     */
    private final String address;



}
