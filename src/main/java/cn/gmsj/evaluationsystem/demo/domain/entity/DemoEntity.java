package cn.gmsj.evaluationsystem.demo.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author 13562
 */
@Data
@ToString
@Entity(name = "all_demo_info")
public class DemoEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6984004830277560755L;

    @NotEmpty(message = "xx不能为空")
    private String demo;

}
