package cn.gmsj.evaluationsystem.specialist.web.res;

import cn.gmsj.evaluationsystem.specialist.domain.enums.ExpertInfoType;
import cn.gmsj.evaluationsystem.specialist.domain.enums.SexType;
import cn.gmsj.evaluationsystem.specialist.domain.entity.ExpertInfoFileEntity;
import cn.gmsj.evaluationsystem.specialist.domain.entity.ExpertInfoImageEntity;
import cn.gmsj.evaluationsystem.specialist.domain.entity.*;
import lombok.Data;
import lombok.ToString;

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

    private SexType sex;

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

    private ExpertInfoImageEntity expertInfoImageEntity;     //照片信息

    private List<ExpertInfoFileEntity> expertInfoFileEntityList;     //上传资料信息

    private ExpertInfoType expertInfoType;     //专家信息状态

}
