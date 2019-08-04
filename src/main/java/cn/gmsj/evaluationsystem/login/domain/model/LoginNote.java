package cn.gmsj.evaluationsystem.login.domain.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 13562
 */
@Data
@ToString
public class LoginNote implements Serializable {

    private static final long serialVersionUID = -3882181874222633697L;

    private String phone;

    private Boolean send = false;

    private String authCode;
}
