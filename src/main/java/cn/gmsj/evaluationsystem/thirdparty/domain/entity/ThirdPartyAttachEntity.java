package cn.gmsj.evaluationsystem.thirdparty.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.specialist.domain.entity.ExpertInfoEntity;
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
@Entity(name = "all_third_party_attach")
public class ThirdPartyAttachEntity extends BaseEntity implements Serializable {
    /**
     * 文件真实路径
     */
    @Lob
    private String path;
    /**
     * 文件名称
     */
    private String name;
    /**
     * uuid
     */
    private String uuid;
    /**
     * uuid式文件名
     */
    private String uuidFileName;

    /**
     * 第三方机构ID
     */
    @JoinColumn(name = "third_party_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private ThirdPartyEntity thirdPartyEntity;


}
