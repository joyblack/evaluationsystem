package cn.gmsj.evaluationsystem.thirdparty.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 第三方机构信息
 */
@Data
@ToString
@Entity(name = "all_third_party")
public class ThirdPartyEntity extends BaseEntity implements Serializable {
    /**
     * 对应登陆账户信息
     */
    @OneToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @NotEmpty(message = "单位全称不能为空")
    private String companyName;

    @NotEmpty(message = "统一社会信用代码不能为空")
    private String socialCreditCode;

    @NotEmpty(message = "省不能为空")
    private String province;

    @NotEmpty(message = "市或区不能为空")
    private String area;

    @NotEmpty(message = "县不能为空")
    private String county;

    @NotEmpty(message = "单位地址不能为空")
    private String companyAddress;

    @NotEmpty(message = "邮政编码不能为空")
    private String postalCode;

    @NotEmpty(message = "单位电话不能为空")
    private String companyTelephone;

    @NotEmpty(message = "单位信箱不能为空")
    private String companyMailbox;

    @NotEmpty(message = "法定代表人不能为空")
    private String legalRepresent;

    @NotEmpty(message = "法定代表人手机号")
    private String legalRepresentPhone;

    @NotEmpty(message = "手机号码不能为空")
    private String phone;

    /**
     * 相关工作主要业绩
     */
    @Lob
    private String relatedWorkAchievement;

}
