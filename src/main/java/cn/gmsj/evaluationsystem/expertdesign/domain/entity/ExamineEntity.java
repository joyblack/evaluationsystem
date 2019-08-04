package cn.gmsj.evaluationsystem.expertdesign.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.projectmanage.domain.entity.ProjectEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 审查记录实体类
 */
@Data
@ToString
@Entity(name = "all_examine_record")
public class ExamineEntity extends BaseEntity implements Serializable {


    @JoinColumn(name = "project_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private ProjectEntity projectEntity;

    @NotNull(message= "审查意见不为空")
    private String examineSuggest;//审查意见

    private Date examineTime;//审查时间

}
