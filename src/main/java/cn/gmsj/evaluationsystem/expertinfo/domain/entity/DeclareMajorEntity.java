package cn.gmsj.evaluationsystem.expertinfo.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auther AliZhou
 * @date 2019/8/1 14:42
 * 申报专业明细
 */
@Data
@ToString
@Entity(name = "all_declare_major")
public class DeclareMajorEntity extends BaseEntity implements Serializable {
    private String declareMajorName;  //申报专业名称

    /**
     * 申报专业所属类型
     */
    @NotNull(message = "申报专业所属类型不能为空")
    @JoinColumn(name = "declare_major_type_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private DeclareMajorTypeEntity declareMajorTypeEntity;
}
