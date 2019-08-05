package cn.gmsj.evaluationsystem.specialist.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @auther AliZhou
 * @date 2019/8/1 14:42
 * 最高学历
 */
@Data
@ToString
@Entity(name = "all_Education")
public class EducationEntity extends BaseEntity implements Serializable {

    private String educationName;  //学历名称
}
