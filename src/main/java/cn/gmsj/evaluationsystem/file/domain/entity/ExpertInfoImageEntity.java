package cn.gmsj.evaluationsystem.file.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.EducationEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.ExpertInfoEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@ToString
@Entity(name = "all_expert_info_image")
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

    @JoinColumn(name = "expert_info_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private ExpertInfoEntity expertInfoEntity;
}
