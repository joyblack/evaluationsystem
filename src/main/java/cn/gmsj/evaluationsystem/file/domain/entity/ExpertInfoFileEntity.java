package cn.gmsj.evaluationsystem.file.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.ExpertInfoEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity(name = "all_expert_info_file")
public class ExpertInfoFileEntity extends BaseEntity {

    private static final long serialVersionUID = -1L;

    /**
     * 文件真实路径
     */
    @Lob
    private String path;
    /**
     * 文件名称
     */
    private String name;
    /**
     * uuid
     */
    private String uuid;
    /**
     * uuid式文件名
     */
    private String uuidFileName;

    @JoinColumn(name = "expert_info_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private ExpertInfoEntity expertInfoEntity;
}
