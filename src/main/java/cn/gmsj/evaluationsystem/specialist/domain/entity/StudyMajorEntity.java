package cn.gmsj.evaluationsystem.specialist.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @auther AliZhou
 * @date 2019/8/1 14:42
 * 学习专业
 */
@Data
@ToString
@Entity(name = "all_study_major")
public class StudyMajorEntity extends BaseEntity implements Serializable {
    private String studyMajorName;  //学习专业名称
}
