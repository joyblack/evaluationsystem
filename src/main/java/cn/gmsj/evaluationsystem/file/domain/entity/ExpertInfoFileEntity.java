package cn.gmsj.evaluationsystem.file.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alan
 */
@Data
@ToString
@Entity(name = "all_file")
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
}
