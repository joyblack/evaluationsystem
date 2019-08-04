package cn.gmsj.evaluationsystem.user.web.req;

import lombok.Data;
import lombok.ToString;

/**
 * Created by XiaoWen on 2019/8/4
 */
@Data
@ToString
public class UserPasswordResetReq {

    private String phone;

    private String authCode;

    private String password;

    private String affirmPassword;
}
