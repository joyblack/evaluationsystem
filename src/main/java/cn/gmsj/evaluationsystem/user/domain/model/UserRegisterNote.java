package cn.gmsj.evaluationsystem.user.domain.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 13562
 */
@Data
@ToString
public class UserRegisterNote implements Serializable {

    private static final long serialVersionUID = 632778794852264516L;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 是否发送
     */
    private Boolean send = false;
    /**
     * 验证码
     */
    private String authCode;
}
