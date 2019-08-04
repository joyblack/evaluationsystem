package cn.gmsj.evaluationsystem.invite.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.invite.enums.Invite;
import cn.gmsj.evaluationsystem.invite.enums.InviteDirection;
import cn.gmsj.evaluationsystem.invite.enums.InviteState;
import cn.gmsj.evaluationsystem.projectmanage.domain.entity.ProjectEntity;
import cn.gmsj.evaluationsystem.projectmanage.enums.ReviewType;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 13562
 * <p>
 * 邀请记录
 */
@Data
@ToString
@Entity(name = "all_project_invite_info")
public class ProjectInviteEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8717194230271539249L;
    /**
     * 能源局项目
     */
    @NotNull(message = "邀请项目不能为空")
    @JoinColumn(name = "project_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private ProjectEntity project;
    /**
     * 邀请组织类型
     */
    @NotNull(message = "邀请类不能为空")
    private Invite invite;

    @NotNull(message = "受邀请用户不能为空")
    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private UserEntity user;
    /**
     * 邀请时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inviteDate = new Date();
    /**
     * 截止时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;

    /**
     * 邀请状态
     */
    private InviteState inviteState = InviteState.AWAIT;
    /**
     * 邀请参与方向
     */
    private InviteDirection inviteDirection;
    /**
     * 设评类型
     */
    private ReviewType reviewType;
}
