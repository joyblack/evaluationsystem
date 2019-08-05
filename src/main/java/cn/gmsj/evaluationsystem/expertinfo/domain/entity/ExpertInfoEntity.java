package cn.gmsj.evaluationsystem.expertinfo.domain.entity;
import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.enums.ExpertInfoType;
import cn.gmsj.evaluationsystem.expertinfo.domain.enums.SexType;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 专家基本信息
 */
@Data
@ToString
@Entity(name = "all_expert_info")
public class ExpertInfoEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6984004830277560755L;

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotNull(message = "性别不能为空")
    private SexType sex;

    @NotEmpty(message = "出生日期不能为空")
    private String birthday;

    @NotEmpty(message = "手机不能为空")
    private String phone;

    @NotEmpty(message = "工作电话不能为空")
    private String telephone;

    @NotEmpty(message = "身份号不能为空")
    private String idCard;

    @NotEmpty(message = "在岗情况不能为空")
    private String isWork;

    @NotEmpty(message = "单位全称不能为空")
    private String companyName;

    @NotEmpty(message = "单位地址不能为空")
    private String companyAddress;

    @NotEmpty(message = "省不能为空")
    private String province;

    @NotEmpty(message = "市或区不能为空")
    private String area;

    @NotEmpty(message = "县不能为空")
    private String county;

    @NotEmpty(message = "邮政编码不能为空")
    private String postalCode;

    @NotEmpty(message = "所在部门不能为空")
    private String department;

    /**
     * 职称类型，高，中，初
     */
    @NotNull(message = "职称类型不能为空")
    @JoinColumn(name = "positional_type_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private PositionalTypeEntity positionalTypeEntity;

    /**
     * 职称名称，建造工程师
     */
    @NotNull(message = "职称名称不能为空")
    @JoinColumn(name = "positional_title_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private PositionalTitleEntity positionalTitleEntity;

    @NotEmpty(message = "单位电话不能为空")
    private String companyTelep;

    @NotEmpty(message = "传真号码不能为空")
    private String fax;

    @NotEmpty(message = "个人信箱不能为空")
    private String mailbox;

    @NotEmpty(message = "毕业院校不能为空")
    private String school;

    /**
     *最高学历
     */
    @NotNull(message = "最高学历不能为空")
    @JoinColumn(name = "education_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private EducationEntity educationEntity;

    @NotEmpty(message = "职业资格不能为空")
    private String seniority;

    /**
     * 所学专业
     */
    @NotEmpty(message = "所学专业不能为空")
    private String studyMajors;

    /**
     * 从事专业
     */
    @NotEmpty(message = "从事专业不能为空")
    private String engagedMajors;

    /**
     * 申报专业
     */
    @NotEmpty(message = "申报专业不能为空")
    private String declareMajors;

    @Lob
    private String resume;    //个人简历
    @Lob
    private String academicSituation;     //发明著作学术论文情况
    @Lob
    private String reward;     //   受奖励情况
    @Lob
    private String researchFinding; //安全生产相关工作主要业绩及研究成果

    private ExpertInfoType expertInfoType;     //专家信息状态
}
