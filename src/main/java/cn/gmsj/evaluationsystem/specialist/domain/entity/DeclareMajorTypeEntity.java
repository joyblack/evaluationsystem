package cn.gmsj.evaluationsystem.specialist.domain.entity;

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
 * 申报专业类型
 */
@Data
@ToString
@Entity(name = "all_declare_major_type")
public class DeclareMajorTypeEntity extends BaseEntity implements Serializable {
    private String declareMajorTypeName;  //申报专业类型名称
}
