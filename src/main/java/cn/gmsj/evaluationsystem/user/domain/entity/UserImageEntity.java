package cn.gmsj.evaluationsystem.user.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by XiaoWen on 2019/8/3
 */
@Data
@ToString
@Entity(name = "all_user_image_info")
public class UserImageEntity extends BaseEntity implements Serializable {


    /**
     * 上传原文件名
     */
    private String correspondName;

    /**
     * 对应名
     */
    private String name;


    private String uuid;

    /**
     * 图片所属用户
     */
    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private UserEntity userEntity;

    /**
     * 存储路径
     */
    @Lob
    private String path;
}
