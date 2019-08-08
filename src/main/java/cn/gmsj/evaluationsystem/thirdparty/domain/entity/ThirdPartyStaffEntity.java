package cn.gmsj.evaluationsystem.thirdparty.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import sun.misc.BASE64Decoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 第三方机构技术人员信息
 */
@Data
@ToString
@Entity(name = "all_third_party_staff")
public class ThirdPartyStaffEntity extends BaseEntity implements Serializable {

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "专业不能为空")
    private String major;

    @NotEmpty(message = "业绩不能为空")
    @Lob
    //@Length(max = 500,message = "至多500字")
    private String achievement;

    /**
     * 技术人员所属第三方机构
     */
    @JoinColumn(name = "third_party_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private ThirdPartyEntity thirdPartyEntity;
}
