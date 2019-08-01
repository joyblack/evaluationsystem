package cn.gmsj.evaluationsystem.expertinfo.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auther AliZhou
 * @date 2019/8/1 14:42
 * 申报专业
 */
@Data
@ToString
@Entity(name = "all_declare_major")
public class DeclareMajorEntity extends BaseEntity implements Serializable {
    private String declareMajorName;  //从事专业名称

    private Integer type;
}
