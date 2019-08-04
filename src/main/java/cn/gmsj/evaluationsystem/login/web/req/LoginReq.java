package cn.gmsj.evaluationsystem.login.web.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 13562
 */
@Data
@ToString
public class LoginReq implements Serializable {

    private static final long serialVersionUID = -2540824054272974385L;
    /**
     * 手机号
     */
    private String phone;

    private String authCode;

    private String username;

    private String password;
}
