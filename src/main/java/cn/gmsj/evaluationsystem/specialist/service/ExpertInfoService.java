package cn.gmsj.evaluationsystem.specialist.service;

import cn.gmsj.evaluationsystem.activityrecord.domain.repository.ActivityRecordRepository;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.specialist.web.res.DeclareMajorArrayRes;
import cn.gmsj.evaluationsystem.specialist.web.res.ExpertInfoRes;
import cn.gmsj.evaluationsystem.specialist.domain.entity.ExpertInfoFileEntity;
import cn.gmsj.evaluationsystem.specialist.domain.entity.ExpertInfoImageEntity;
import cn.gmsj.evaluationsystem.specialist.domain.repository.ExpertInfoFileRepository;
import cn.gmsj.evaluationsystem.specialist.domain.repository.ExpertInfoImageRepository;
import cn.gmsj.evaluationsystem.specialist.domain.entity.*;
import cn.gmsj.evaluationsystem.specialist.domain.repository.*;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.utils.IdNumberUtil;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.UpdateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @author 13562
 */
@Service
public class ExpertInfoService {

    @Autowired
    private ExpertInfoRepository expertInfoRepository;

    @Autowired
    private PositionalTypeRepository positionalTypeRepository;

    @Autowired
    private PositionalTitleRepository positionalTitleRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private StudyMajorRepository studyMajorRepository;

    @Autowired
    private EngagedMajorRepository engagedMajorRepository;

    @Autowired
    private DeclareMajorRepository declareMajorRepository;

    @Autowired
    private DeclareMajorTypeRepository declareMajorTypeRepository;

    @Autowired
    private ExpertInfoImageRepository expertInfoImageRepository;

    @Autowired
    private ExpertInfoFileRepository expertInfoFileRepository;
    @Autowired
    private ActivityRecordRepository activityRecordRepository;

    public JSONObject updateData(ExpertInfoEntity expertInfoEntity, UserEntity userEntity) {
        if (null == expertInfoEntity.getId()) {
            expertInfoEntity.setId(0L);
        } else {
            //把数据库的数据拷贝到目标数据类中，取出目标数据不为空的，不为空的做修改，为空的用数据库的数据
            UpdateUtil.copyProperties(expertInfoRepository.findAllById(expertInfoEntity.getId()), expertInfoEntity);
            expertInfoEntity.setUpdateTime(new Date());
        }
        List<ExpertInfoEntity> expertInfoEntityList = expertInfoRepository.findAllByIdCardAndIdNot(expertInfoEntity.getIdCard(), expertInfoEntity.getId());
        if (expertInfoEntityList != null && expertInfoEntityList.size() > 0) {
            throw new WafException("", "身份证号码重复", HttpStatus.NOT_ACCEPTABLE);
        }
        List<ExpertInfoEntity> expertInfoEntities = expertInfoRepository.findAllByPhoneAndIdNot(expertInfoEntity.getPhone(), expertInfoEntity.getId());
        if (expertInfoEntities != null && expertInfoEntities.size() > 0) {
            throw new WafException("", "手机重复", HttpStatus.NOT_ACCEPTABLE);
        }
//        if(ExpertInfoType.COMMIT.equals(expertInfoEntity.getExpertInfoType())){
//            ActivityRecordUtil.createActivityRecordByOneself(userEntity,ActivityRecord.STORAGE_APPLICATION, ActivityRecordType.SUBMIT,activityRecordRepository);
//        }
        return ResultUtil.success(expertInfoRepository.save(expertInfoEntity));
    }

    public JSONObject getExpertInfo(UserEntity userEntity) {
        ExpertInfoEntity expertInfoEntity = expertInfoRepository.findAllByIdCard(userEntity.getIdNumber());
        ExpertInfoRes expertInfoRes = new ExpertInfoRes();
        expertInfoRes.setName(userEntity.getName());
        expertInfoRes.setSex(IdNumberUtil.getSex(userEntity.getIdNumber()));
        expertInfoRes.setBirthday(IdNumberUtil.getBirthday(userEntity.getIdNumber()));
        expertInfoRes.setPhone(userEntity.getPhone());
        expertInfoRes.setIdCard(userEntity.getIdNumber());
        if(!ObjectUtils.isEmpty(expertInfoEntity)){
            PositionalTypeEntity positionalTypeEntity = positionalTypeRepository.findAllById(expertInfoEntity.getPositionalTypeEntity().getId());
            PositionalTitleEntity positionalTitleEntity = positionalTitleRepository.findAllById(expertInfoEntity.getPositionalTitleEntity().getId());
            EducationEntity educationEntity = educationRepository.findAllById(expertInfoEntity.getEducationEntity().getId());
            //获取所学专业
            List<Long> studyMajorsStr = new ArrayList<>();
            if (expertInfoEntity.getStudyMajors().contains("-")) {
                String[] studyMajors = expertInfoEntity.getStudyMajors().split("-");
                for (int i = 0; i < studyMajors.length; i++) {
                    studyMajorsStr.add(new Long(studyMajors[i]));
                }
            } else {
                studyMajorsStr.add(new Long(expertInfoEntity.getStudyMajors()));
            }
            List<StudyMajorEntity> studyMajorEntityList = studyMajorRepository.findAllByIdIn(studyMajorsStr);
            //获取从事专业
            List<Long> engagedMajorsStr = new ArrayList<>();
            if (expertInfoEntity.getStudyMajors().contains("-")) {
                String[] engagedMajors = expertInfoEntity.getEngagedMajors().split("-");
                for (int i = 0; i < engagedMajors.length; i++) {
                    engagedMajorsStr.add(new Long(engagedMajors[i]));
                }
            } else {
                engagedMajorsStr.add(new Long(expertInfoEntity.getEngagedMajors()));
            }
            List<EngagedMajorEntity> engagedMajorEntityList = engagedMajorRepository.findAllByIdIn(engagedMajorsStr);
            //获取申报专业
            List<Long> declareMajorsStr = new ArrayList<>();
            if (expertInfoEntity.getStudyMajors().contains("-")) {
                String[] declareMajors = expertInfoEntity.getDeclareMajors().split("-");
                for (int i = 0; i < declareMajors.length; i++) {
                    declareMajorsStr.add(new Long(declareMajors[i]));
                }
            } else {
                declareMajorsStr.add(new Long(expertInfoEntity.getDeclareMajors()));
            }
            List<String> declareMajorTypeNames = new ArrayList<>();
            List<DeclareMajorTypeEntity> declareMajorTypeEntityList = declareMajorTypeRepository.findAll();
            for (DeclareMajorTypeEntity declareMajorTypeEntity : declareMajorTypeEntityList) {
                declareMajorTypeNames.add(declareMajorTypeEntity.getDeclareMajorTypeName());
            }
            List<DeclareMajorEntity> declareMajorEntityList = declareMajorRepository.findAllByIdIn(declareMajorsStr);
            Map<String, List<DeclareMajorArrayRes>> decMap = new HashMap<>();
            List<DeclareMajorArrayRes> declareMajorArrayResList = new ArrayList<>();
            for (DeclareMajorEntity declareMajorEntity : declareMajorEntityList) {
                String declareMajorTypeName = declareMajorEntity.getDeclareMajorTypeEntity().getDeclareMajorTypeName();
                declareMajorArrayResList = decMap.get(declareMajorTypeName);
                if (declareMajorArrayResList == null) {
                    declareMajorArrayResList = new ArrayList<>();
                }
                DeclareMajorArrayRes declareMajorArrayRes = new DeclareMajorArrayRes();
                declareMajorArrayRes.setId(declareMajorEntity.getId());
                declareMajorArrayRes.setDeclareMajorName(declareMajorEntity.getDeclareMajorName());
                declareMajorArrayResList.add(declareMajorArrayRes);
                decMap.put(declareMajorTypeName, declareMajorArrayResList);
            }
            for (int i = 0; i < declareMajorTypeNames.size(); i++) {
                String typeName = declareMajorTypeNames.get(i);
                List<DeclareMajorArrayRes> oldDeclareMajorArrayRes = decMap.get(typeName);
                if (oldDeclareMajorArrayRes == null) {
                    oldDeclareMajorArrayRes = new ArrayList<>();
                    decMap.put(typeName, oldDeclareMajorArrayRes);
                }
            }
            expertInfoRes.setId(expertInfoEntity.getId());
            expertInfoRes.setTelephone(expertInfoEntity.getTelephone());
            expertInfoRes.setIsWork(expertInfoEntity.getIsWork());
            expertInfoRes.setCompanyName(expertInfoEntity.getCompanyName());
            expertInfoRes.setCompanyAddress(expertInfoEntity.getCompanyAddress());
            expertInfoRes.setProvince(expertInfoEntity.getProvince());
            expertInfoRes.setArea(expertInfoEntity.getArea());
            expertInfoRes.setCounty(expertInfoEntity.getCounty());
            expertInfoRes.setPostalCode(expertInfoEntity.getPostalCode());
            expertInfoRes.setDepartment(expertInfoEntity.getDepartment());
            expertInfoRes.setPositionalTypeEntity(positionalTypeEntity);
            expertInfoRes.setPositionalTitleEntity(positionalTitleEntity);
            expertInfoRes.setCompanyTelep(expertInfoEntity.getCompanyTelep());
            expertInfoRes.setFax(expertInfoEntity.getFax());
            expertInfoRes.setMailbox(expertInfoEntity.getMailbox());
            expertInfoRes.setSchool(expertInfoEntity.getSchool());
            expertInfoRes.setEducationEntity(educationEntity);
            expertInfoRes.setSeniority(expertInfoEntity.getSeniority());
            expertInfoRes.setStudyMajors(studyMajorEntityList);
            expertInfoRes.setEngagedMajors(engagedMajorEntityList);
            expertInfoRes.setDeclareMajors(decMap);
            expertInfoRes.setResume(expertInfoEntity.getResume());
            expertInfoRes.setAcademicSituation(expertInfoEntity.getAcademicSituation());
            expertInfoRes.setReward(expertInfoEntity.getReward());
            expertInfoRes.setResearchFinding(expertInfoEntity.getResearchFinding());
            expertInfoRes.setExpertInfoType(expertInfoEntity.getExpertInfoType());
        }
        List<ExpertInfoImageEntity> expertInfoImageEntityList=expertInfoImageRepository.findAllByUserEntityOrderByCreateTimeDesc(userEntity);
        if(ObjectUtils.isEmpty(expertInfoImageEntityList)){
            expertInfoRes.setExpertInfoImageEntity(new ExpertInfoImageEntity());
        }else {
            expertInfoRes.setExpertInfoImageEntity(expertInfoImageEntityList.get(0));
        }
        List<ExpertInfoFileEntity>  expertInfoFileEntityList=expertInfoFileRepository.findAllByOrderByCreateTimeDesc();
        if(ObjectUtils.isEmpty(expertInfoFileEntityList)){
            expertInfoFileEntityList=new ArrayList<>();
            expertInfoRes.setExpertInfoFileEntityList(expertInfoFileEntityList);
        }else {
            expertInfoRes.setExpertInfoFileEntityList(expertInfoFileEntityList);
        }
        return ResultUtil.success(expertInfoRes);
    }

    public JSONObject getPositionalTypeList() {
        List<PositionalTypeEntity> positionalTypeEntityList = positionalTypeRepository.findAll();
        if (positionalTypeEntityList.size() == 0) {
            throw new WafException("", "职称类型不存在，请联系管理员", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(positionalTypeEntityList);
    }

    public JSONObject getPositionalTitleList() {
        List<PositionalTitleEntity> positionalTitleEntityList = positionalTitleRepository.findAll();
        if (positionalTitleEntityList.size() == 0) {
            throw new WafException("", "职称名称不存在，请联系管理员", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(positionalTitleEntityList);
    }

    public JSONObject getEducationList() {
        List<EducationEntity> educationEntityList = educationRepository.findAll();
        if (educationEntityList.size() == 0) {
            throw new WafException("", "最高学历信息不存在，请联系管理员", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(educationEntityList);
    }

    public JSONObject getStudyMajorList() {
        List<StudyMajorEntity> studyMajorEntityList = studyMajorRepository.findAll();
        if (studyMajorEntityList.size() == 0) {
            throw new WafException("", "所学专业不存在，请联系管理员", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(studyMajorEntityList);
    }

    public JSONObject getEngagedMajorList() {
        List<EngagedMajorEntity> engagedMajorEntityList = engagedMajorRepository.findAll();
        if (engagedMajorEntityList.size() == 0) {
            throw new WafException("", "从事专业不存在，请联系管理员", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(engagedMajorEntityList);
    }

    public JSONObject getDeclareMajorList() {
        List<DeclareMajorTypeEntity> declareMajorTypeEntityList=declareMajorTypeRepository.findAll();
        Map<String,DeclareMajorTypeEntity> declareMajorTypeEntityMap=new HashMap<>();
        for(DeclareMajorTypeEntity declareMajorTypeEntity:declareMajorTypeEntityList){
            declareMajorTypeEntityMap.put(declareMajorTypeEntity.getDeclareMajorTypeName(),declareMajorTypeEntity);
        }
        Map<String,List<DeclareMajorArrayRes>> declareMajorArrayResMap=new HashMap<>();
        List<DeclareMajorArrayRes> declareMajorArrayResList=new ArrayList<>();
        List<DeclareMajorEntity> declareMajorEntityList = declareMajorRepository.findAll();
        for (DeclareMajorEntity declareMajorEntity : declareMajorEntityList) {
            String declareMajorTypeName=declareMajorEntity.getDeclareMajorTypeEntity().getDeclareMajorTypeName();
            declareMajorArrayResList=declareMajorArrayResMap.get(declareMajorTypeName);
            if (declareMajorArrayResList == null) {
                declareMajorArrayResList = new ArrayList<>();
            }
            DeclareMajorArrayRes declareMajorArrayRes = new DeclareMajorArrayRes();
            declareMajorArrayRes.setId(declareMajorEntity.getId());
            declareMajorArrayRes.setDeclareMajorName(declareMajorEntity.getDeclareMajorName());
            declareMajorArrayResList.add(declareMajorArrayRes);
            declareMajorArrayResMap.put(declareMajorTypeName, declareMajorArrayResList);
        }
        return ResultUtil.success(declareMajorArrayResMap);
    }
}
