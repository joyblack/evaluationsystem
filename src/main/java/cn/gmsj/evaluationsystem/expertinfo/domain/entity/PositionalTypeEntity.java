package cn.gmsj.evaluationsystem.expertinfo.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @auther AliZhou
 * @date 2019/8/1 14:42
 * 职称类型
 */
@Data
@ToString
@Entity(name = "all_positional_type")
public class PositionalTypeEntity extends BaseEntity implements Serializable {
    private String positionalTypeName;   // 职称类型名称
}
