package cn.gmsj.evaluationsystem.common.domain.entity;

import cn.gmsj.evaluationsystem.enums.UserDataType;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体基础类
 *
 * @author Alan
 */
@MappedSuperclass
@Data
@ToString
public class BaseEntity implements Serializable {

    public BaseEntity() {
    }

    private static final long serialVersionUID = -1L;

    /**
     * 自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户数据类型
     */
    private UserDataType userDataType;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "创建日期不能为空")
    private Date createTime = new Date();

    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "修改日期不能为空")
    private Date updateTime = new Date();
    /**
     * 备注
     **/
    @Lob
    private String remarks;
}
