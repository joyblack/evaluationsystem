package cn.gmsj.evaluationsystem.user.domain.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@ToString
public class UserPassordResetNote implements Serializable {


    private String phone;

    private Boolean send = false;

    private String authCode;
}
