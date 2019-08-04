package cn.gmsj.evaluationsystem.expertdesign.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.expertdesign.enums.InviteType;
import cn.gmsj.evaluationsystem.expertdesign.enums.Status;
import cn.gmsj.evaluationsystem.invite.enums.Invite;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Entity(name = "all_invite_record")
public class InviteEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6984004830277560755L;

//    @NotNull(message = "邀请项目不能为空")
//    @JoinColumn(name = "project_id")
//    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
//    private String energyProject;//能源局项目

    private Invite invite;//邀请类型

    @NotNull(message = "邀请专家不能为空")
    @JoinColumn(name = "user_reg_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private UserEntity userRegEntity;//邀请专家

    private  String inviteThirdPart;//邀请第三方机构

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inviteTime;//邀请时间

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stopTime;//截止时间

    private Status status;//是否接受枚举

    private InviteType inviteType;//邀请类型枚举

    @NotNull(message = "审查不能为空")
    @JoinColumn(name = "examine_record_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private ExamineEntity examineEntity;



}
