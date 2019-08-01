package cn.gmsj.evaluationsystem.expertinfo.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @auther AliZhou
 * @date 2019/8/1 14:42
 * 从事专业
 */
@Data
@ToString
@Entity(name = "all_engaged_major")
public class EngagedMajorEntity extends BaseEntity implements Serializable {
    private String engagedMajorName;  //从事专业名称
}
