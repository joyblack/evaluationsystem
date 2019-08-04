package cn.gmsj.evaluationsystem.activityrecord.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.userreg.domain.entity.UserRegEntity;
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
 * Created by XiaoWen on 2019/8/3
 */
@Data
@ToString
@Entity(name = "all_activity_record")
public class ActivityRecordEntity extends BaseEntity implements Serializable {

    /**
     * 用户
     */
    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private UserEntity userEntity;

    /**
     * 日志消息
     */
    private String message;

}
