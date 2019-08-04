package cn.gmsj.evaluationsystem.government.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.enums.UserDataType;
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
@Entity(name = "all_government_user_info")
public class GovernmentUserEntity extends BaseEntity implements Serializable {

    /**
     * 姓名
     */
    @NotNull(message = "名字不能为空")
    @Column(name = "name")
    private String name;

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
     * 用户数据类型
     */
    @NotNull(message = "用户数据类型不能为空")
    private UserDataType userDataType;







}
