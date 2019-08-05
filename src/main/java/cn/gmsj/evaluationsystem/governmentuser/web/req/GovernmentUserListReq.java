package cn.gmsj.evaluationsystem.governmentuser.web.req;

import lombok.Data;
import lombok.ToString;

/**
 * Created by XiaoWen on 2019/8/4
 */
@Data
@ToString
public class GovernmentUserListReq {

    private String oldPassword;

    private String password;

    private String affirmPassword;
}
