package cn.gmsj.evaluationsystem.user.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by XiaoWen on 2019/8/3
 * @author Administrator
 */
@Data
@ToString
@Entity(name = "all_user_info")
public class UserEntity extends BaseEntity implements Serializable {

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 身份证号
     */
    @Column(name = "id_number")
    private String idNumber;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @Column(name = "password")
    private String password;

    /**
     * 密码确认
     */
    @Transient
    private String affirmPassword;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    @Column(name = "phone")
    private String phone;

    /**
     * 单位名称
     */
    @Column(name = "unit_name")
    private String unitName;

    /**
     * 统一社会信用代码
     */
    @Column(name = "social_credit_code")
    private String socialCreditCode;

    /**
     * 营业执照
     */
    @Column(name = "business_licence")
    private String businessLicence;






}
