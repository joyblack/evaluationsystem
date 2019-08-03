package cn.gmsj.evaluationsystem.expertinfo.service;

import cn.gmsj.evaluationsystem.demo.web.req.DemoListReq;
import cn.gmsj.evaluationsystem.demo.web.req.DemoReq;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.*;
import cn.gmsj.evaluationsystem.expertinfo.domain.repository.*;
import cn.gmsj.evaluationsystem.expertinfo.web.req.ExpertInfoReq;
import cn.gmsj.evaluationsystem.expertinfo.web.res.DeclareMajorListRes;
import cn.gmsj.evaluationsystem.expertinfo.web.res.ExpertInfoRes;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.UpdateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public JSONObject updateData(ExpertInfoEntity expertInfoEntity) {
        if (null == expertInfoEntity.getId()) {
            expertInfoEntity.setId(0L);
        } else {
            //把数据库的数据拷贝到目标数据类中，取出目标数据不为空的，不为空的做修改，为空的用数据库的数据
            UpdateUtil.copyProperties(expertInfoRepository.findAllById(expertInfoEntity.getId()), expertInfoEntity);
            expertInfoEntity.setUpdateTime(new Date());
        }
        List<ExpertInfoEntity> expertInfoEntityList=expertInfoRepository.findAllByIdCardAndIdNot(expertInfoEntity.getIdCard(),expertInfoEntity.getId());
        if (expertInfoEntityList != null && expertInfoEntityList.size() > 0) {
            throw new WafException("", "身份证号码重复", HttpStatus.NOT_ACCEPTABLE);
        }
        List<ExpertInfoEntity> expertInfoEntities=expertInfoRepository.findAllByPhoneAndIdNot(expertInfoEntity.getPhone(),expertInfoEntity.getId());
        if (expertInfoEntities != null && expertInfoEntities.size() > 0) {
            throw new WafException("", "手机重复", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(expertInfoRepository.save(expertInfoEntity));
    }

    public JSONObject getExpertInfo(ExpertInfoReq expertInfoReq) {
        ExpertInfoEntity expertInfoEntity=expertInfoRepository.
                findAllByIdCardAndExpertInfoType(expertInfoReq.getIdCard(),expertInfoReq.getExpertInfoType());
        PositionalTypeEntity positionalTypeEntity=positionalTypeRepository.findAllById(expertInfoEntity.getPositionalTypeEntity().getId());
        PositionalTitleEntity positionalTitleEntity=positionalTitleRepository.findAllById(expertInfoEntity.getPositionalTitleEntity().getId());
        EducationEntity educationEntity=educationRepository.findAllById(expertInfoEntity.getEducationEntity().getId());
        //获取所学专业
        List<Long>studyMajorsStr=new ArrayList<>();
        if(expertInfoEntity.getStudyMajors().contains("-")){
            String[] studyMajors=expertInfoEntity.getStudyMajors().split("-");
            for(int i=0;i<studyMajors.length;i++){
                studyMajorsStr.add(new Long(studyMajors[i]));
            }
        }else {
            studyMajorsStr.add(new Long(expertInfoEntity.getStudyMajors()));
        }
        List<StudyMajorEntity> studyMajorEntityList=studyMajorRepository.findAllByIdIn(studyMajorsStr);
        //获取从事专业
        List<Long>engagedMajorsStr=new ArrayList<>();
        if(expertInfoEntity.getStudyMajors().contains("-")){
            String[] engagedMajors=expertInfoEntity.getEngagedMajors().split("-");
            for(int i=0;i<engagedMajors.length;i++){
                engagedMajorsStr.add(new Long(engagedMajors[i]));
            }
        }else {
            engagedMajorsStr.add(new Long(expertInfoEntity.getEngagedMajors()));
        }
        List<EngagedMajorEntity> engagedMajorEntityList=engagedMajorRepository.findAllByIdIn(engagedMajorsStr);
        //获取申报专业
        List<Long>declareMajorsStr=new ArrayList<>();
        if(expertInfoEntity.getStudyMajors().contains("-")){
            String[] declareMajors=expertInfoEntity.getDeclareMajors().split("-");
            for(int i=0;i<declareMajors.length;i++){
                declareMajorsStr.add(new Long(declareMajors[i]));
            }
        }else {
            declareMajorsStr.add(new Long(expertInfoEntity.getDeclareMajors()));
        }
        List<DeclareMajorEntity> declareMajorEntityList=declareMajorRepository.findAllByIdIn(declareMajorsStr);
        ExpertInfoRes expertInfoRes=new ExpertInfoRes();
        expertInfoRes.setId(expertInfoEntity.getId());
        expertInfoRes.setName(expertInfoEntity.getName());
        expertInfoRes.setSex(expertInfoEntity.getSex());
        expertInfoRes.setBirthday(expertInfoEntity.getBirthday());
        expertInfoRes.setPhone(expertInfoEntity.getPhone());
        expertInfoRes.setTelephone(expertInfoEntity.getTelephone());
        expertInfoRes.setIdCard(expertInfoEntity.getIdCard());
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
        expertInfoRes.setDeclareMajors(declareMajorEntityList);
        expertInfoRes.setResume(expertInfoEntity.getResume());
        expertInfoRes.setAcademicSituation(expertInfoEntity.getAcademicSituation());
        expertInfoRes.setReward(expertInfoEntity.getReward());
        expertInfoRes.setResearchFinding(expertInfoEntity.getResearchFinding());
        expertInfoRes.setPictureUrl(expertInfoEntity.getPictureUrl());
        expertInfoRes.setMaterialUrl(expertInfoEntity.getMaterialUrl());
        expertInfoRes.setExpertInfoType(expertInfoEntity.getExpertInfoType());
        return ResultUtil.success(expertInfoRes);
    }

    public JSONObject getPositionalTypeList() {
        List<PositionalTypeEntity> positionalTypeEntityList=positionalTypeRepository.findAll();
        if(positionalTypeEntityList.size()==0){
            throw new WafException("", "职称类型不存在，请联系管理员", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(positionalTypeEntityList);
    }

    public JSONObject getPositionalTitleList() {
        List<PositionalTitleEntity> positionalTitleEntityList=positionalTitleRepository.findAll();
        if(positionalTitleEntityList.size()==0){
            throw new WafException("", "职称名称不存在，请联系管理员", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(positionalTitleEntityList);
    }

    public JSONObject getEducationList() {
        List<EducationEntity> educationEntityList=educationRepository.findAll();
        if(educationEntityList.size()==0){
            throw new WafException("", "最高学历信息不存在，请联系管理员", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(educationEntityList);
    }

    public JSONObject getStudyMajorList() {
        List<StudyMajorEntity> studyMajorEntityList=studyMajorRepository.findAll();
        if(studyMajorEntityList.size()==0){
            throw new WafException("", "所学专业不存在，请联系管理员", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(studyMajorEntityList);
    }

    public JSONObject getEngagedMajorList() {
        List<EngagedMajorEntity> engagedMajorEntityList=engagedMajorRepository.findAll();
        if(engagedMajorEntityList.size()==0){
            throw new WafException("", "从事专业不存在，请联系管理员", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(engagedMajorEntityList);
    }

    public JSONObject getDeclareMajorList() {
        List<DeclareMajorEntity> declareMajorEntityList=declareMajorRepository.findAll();
        List<DeclareMajorTypeEntity> declareMajorTypeEntityList=declareMajorTypeRepository.findAll();
        JSONObject declareMajorTypeJson=new JSONObject();
        for(DeclareMajorTypeEntity declareMajorTypeEntity:declareMajorTypeEntityList){
            declareMajorTypeJson.put(declareMajorTypeEntity.getId().toString(),declareMajorTypeEntity);
        }
        List<DeclareMajorListRes>declareMajorListResList=new ArrayList<>();
        for(DeclareMajorEntity declareMajorEntity:declareMajorEntityList){
            DeclareMajorTypeEntity declareMajorTypeEntity=(DeclareMajorTypeEntity)declareMajorTypeJson.
                    get(declareMajorEntity.getDeclareMajorTypeEntity().getId().toString());
            if(declareMajorTypeEntity==null){
                throw new WafException("", "申报专业类型不存在，请联系管理员", HttpStatus.NOT_ACCEPTABLE);
            }
            DeclareMajorListRes declareMajorListRes=new DeclareMajorListRes();
            declareMajorListRes.setId(declareMajorEntity.getId());
            declareMajorListRes.setDeclareMajorName(declareMajorEntity.getDeclareMajorName());
            declareMajorListRes.setDeclareMajorTypeName(declareMajorTypeEntity.getDeclareMajorTypeName());
            declareMajorListRes.setDeclareMajorTypeId(declareMajorTypeEntity.getId());
            declareMajorListResList.add(declareMajorListRes);
        }
        return ResultUtil.success(declareMajorListResList);
    }
}
