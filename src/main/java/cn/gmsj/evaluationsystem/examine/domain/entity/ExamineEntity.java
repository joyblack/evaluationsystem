package cn.gmsj.evaluationsystem.examine.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.invite.domain.entity.ProjectInviteEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by XiaoWen on 2019/8/3
 */
@Data
@ToString
@Entity(name = "all_examine_info")
public class ExamineEntity extends BaseEntity implements Serializable {

    /**
     * 邀请记录
     */
    @NotNull(message = "邀请记录不能为空")
    @JoinColumn(name = "project_invite_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private ProjectInviteEntity projectInvite;

    @Column(name = "suggestion")
    @Lob
    private String suggestion;

}
