package cn.gmsj.evaluationsystem.file.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by XiaoWen on 2019/8/3
 */
@Data
@ToString
@Entity(name = "all_experinfo_image")
public class ExpertInfoImageEntity extends BaseEntity implements Serializable {


    /**
     * 上传原文件名
     */
    private String name;

    /**
     * 对应名
     */
    private String uuidName;


    private String uuid;

    /**
     * 存储路径
     */
    @Lob
    private String path;
}
