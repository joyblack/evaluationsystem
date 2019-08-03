package cn.gmsj.evaluationsystem.projectmanage.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liuwei
 */
@Data
@ToString
@Entity(name = "all_project_info")
public class ProjectEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6984004830277560755L;

    @NotEmpty(message = "项目名不能为空")
    private String projectName;
    /**
     * 地区
     */
    @NotEmpty(message = "地区名不能为空")
    private String region;
    /**
     * 项目性质
     */
    @NotEmpty(message = "项目性质不能为空")
    private String projectNature;
    /**
     * 总投资额
     */
    @NotNull(message = "总投资额不能为空")
    private BigDecimal tolMoney;
    /**
     * 项目规模
     */
    @NotEmpty(message = "项目规模不能为空")
    private String projectScal;
    /**
     * 项目工期
     */
    @NotNull(message = "项目工期不能为空")
    private Integer projectMonth;
    /**
     * 服务年限
     */
    @NotNull(message = "服务年限不能为空")
    private Integer serveYear;
    /**
     * 保有储量
     */
    @NotNull(message = "保有储量不能为空")
    private BigDecimal reserveAmount;
    /**
     * 可采储量
     */
    @NotNull(message = "可采储量不能为空")
    private BigDecimal recoverableAmount;
    /**
     * 瓦斯等级
     */
    @NotEmpty(message = "瓦斯等级不能为空")
    private String gasGrade;
    /**
     * 项目简介
     */
    @NotEmpty(message = "项目简介名不能为空")
    private String projectIntro;
    /**
     * 项目内容
     */
    @NotEmpty(message = "项目内容不能为空")
    private String projectContent;
    /**
     * 上传项目的文件
     */
    @NotEmpty(message = "上传项目不能为空")
    private String projectPaperUrl;
    /**
     * 外部链接
     */
    @NotEmpty(message = "外部链接不能为空")
    private String projectLinks;
    /**
     * 提取码
     */
    @NotEmpty(message = "提取码不能为空")
    private String ftchCode;
}
