package cn.gmsj.evaluationsystem.expertverify.service;

import cn.gmsj.evaluationsystem.activityrecord.domain.repository.ActivityRecordRepository;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.*;
import cn.gmsj.evaluationsystem.expertinfo.domain.repository.*;
import cn.gmsj.evaluationsystem.expertinfo.web.res.DeclareMajorArrayRes;
import cn.gmsj.evaluationsystem.expertinfo.web.res.ExpertInfoRes;
import cn.gmsj.evaluationsystem.expertverify.web.req.ExpertVerifyListReq;
import cn.gmsj.evaluationsystem.file.domain.entity.ExpertInfoFileEntity;
import cn.gmsj.evaluationsystem.file.domain.entity.ExpertInfoImageEntity;
import cn.gmsj.evaluationsystem.file.domain.repository.ExpertInfoFileRepository;
import cn.gmsj.evaluationsystem.file.domain.repository.ExpertInfoImageRepository;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.utils.IdNumberUtil;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.StringUtil;
import cn.gmsj.evaluationsystem.utils.UpdateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author 13562
 */
@Service
public class ExpertVerifyService {

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

    public JSONObject getExpertVerifyList(UserEntity userEntity, ExpertVerifyListReq expertVerifyListReq) {
        Pageable pageable = PageRequest.of(expertVerifyListReq.getPage() - 1, expertVerifyListReq.getSize());
        Page<ExpertInfoEntity> expertInfoEntities = expertInfoRepository.findAll(
                new Specification<ExpertInfoEntity>() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                        List<Predicate> predicates = new ArrayList<Predicate>();
                        if (StringUtil.isNotEmpty(expertVerifyListReq.getSearch())) {
                            predicates.add(cb.or(
                                    cb.like(root.<String>get("name"), "%" + expertVerifyListReq.getSearch() + "%"),
                                    cb.like(root.<String>get("idCard"), "%" + expertVerifyListReq.getSearch() + "%")
                                    //cb.like(root.<String>get("expertInfoType"), "%" + expertVerifyListReq.getSearch() + "%")
                            ));
                        }
                        if (StringUtil.isNotEmpty(expertVerifyListReq.getName())) {
                            predicates.add(cb.like(root.<String>get("name"), "%" + expertVerifyListReq.getName() + "%"));
                        }
                        if (StringUtil.isNotEmpty(expertVerifyListReq.getIdCard())) {
                            predicates.add(cb.like(root.<String>get("idCard"), "%" + expertVerifyListReq.getIdCard() + "%"));
                        }
//                        if (StringUtil.isNotEmpty(expertVerifyListReq.getExpertInfoType().getDescribe())) {
//                            predicates.add(cb.like(root.<String>get("expertInfoType"), "%" + expertVerifyListReq.getExpertInfoType().getDescribe() + "%"));
//                        }
                        query.where(predicates.toArray(new Predicate[predicates.size()]));
                        query.orderBy(cb.desc(root.<Date>get("createTime").as(Date.class)));
                        return query.getRestriction();
                    }
                },
                pageable);
        return ResultUtil.pageSuccess(expertInfoEntities.getContent(), expertInfoEntities.getTotalElements());
    }
}


