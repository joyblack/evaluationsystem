package cn.gmsj.evaluationsystem.user.domain.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author Administrator
 */
@Data
@ToString
public class UserCheck implements Serializable {

    private String phone;

    private String idNumber;

    private String socialCreditCode;
}
