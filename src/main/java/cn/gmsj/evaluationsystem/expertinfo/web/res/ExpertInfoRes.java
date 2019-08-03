package cn.gmsj.evaluationsystem.expertinfo.web.res;

import cn.gmsj.evaluationsystem.expertinfo.domain.entity.*;
import cn.gmsj.evaluationsystem.expertinfo.domain.enums.ExpertInfoType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * @auther AliZhou
 * @date 2019/8/2 10:54
 */
@Data
@ToString
public class ExpertInfoRes {
    private Long id;

    private String name;

    private String sex;

    private String birthday;

    private String phone;

    private String telephone;

    private String idCard;

    private String isWork;

    private String companyName;

    private String companyAddress;

    private String province;

    private String area;

    private String county;

    private String postalCode;

    private String department;

    private PositionalTypeEntity positionalTypeEntity;

    private PositionalTitleEntity positionalTitleEntity;

    private String companyTelep;

    private String fax;

    private String mailbox;

    private String school;

    private EducationEntity educationEntity;

    private String seniority;

    private List<StudyMajorEntity> studyMajors;

    private List<EngagedMajorEntity> engagedMajors;

    private Map<String,List<DeclareMajorArrayRes>> declareMajors;

    private String resume;    //个人简历

    private String academicSituation;     //发明著作学术论文情况

    private String reward;     //   受奖励情况

    private String researchFinding; //安全生产相关工作主要业绩及研究成果

    private String pictureUrl;     //照片路径。

    private String materialUrl;     //上传资料路径。

    private ExpertInfoType expertInfoType;     //专家信息状态



}
