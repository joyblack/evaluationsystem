package cn.gmsj.evaluationsystem.userreg.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.enums.UserDataType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 专家用户注册
 */
@Data
@ToString
@Entity(name = "all_user_reg")
public class UserRegEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;


    private UserDataType userDataType;

	private String uname;
	@NotEmpty(message=	"不能为空")
	private String numberId;
	
	private String password;
	@Transient//临时注解
	private  String repassword;
	@NotEmpty(message=	"不能为空")
	private String phone;
	@Transient
	private String code;//验证码

	private String companyName;//单位名称

	private String  creditCode;//信用代码

	private String businessImage; //营业执照
}
