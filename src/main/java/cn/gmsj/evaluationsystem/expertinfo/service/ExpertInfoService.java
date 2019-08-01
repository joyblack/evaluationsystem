package cn.gmsj.evaluationsystem.expertinfo.service;

import cn.gmsj.evaluationsystem.demo.web.req.DemoListReq;
import cn.gmsj.evaluationsystem.demo.web.req.DemoReq;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.ExpertInfoEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.repository.ExpertInfoRepository;
import cn.gmsj.evaluationsystem.expertinfo.web.req.ExpertInfoReq;
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

    public JSONObject updateData(ExpertInfoEntity expertInfoEntity) {
        if (null == expertInfoEntity.getId()) {
            expertInfoEntity.setId(0L);
        } else {
            UpdateUtil.copyProperties(expertInfoRepository.findAllById(expertInfoEntity.getId()), expertInfoEntity);
            expertInfoEntity.setUpdateTime(new Date());
        }

        return ResultUtil.success(expertInfoRepository.save(expertInfoEntity));
    }

//    public JSONObject getDataById(ExpertInfoReq expertInfoReq) {
//        if (demoReq.getId() != null) {
//            ExpertInfoEntity demoEntity = demoRepository.findAllById(demoReq.getId());
//            if (demoEntity != null) {
//                return ResultUtil.success(demoEntity);
//            } else {
//                throw new WafException("", "xxx不存在", HttpStatus.NOT_ACCEPTABLE);
//            }
//        } else {
//            throw new WafException("", "xxx不存在", HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//    public JSONObject deleteDataById(DemoReq demoReq) {
//        if (demoReq.getId() != null) {
//            ExpertInfoEntity demoEntity = demoRepository.findAllById(demoReq.getId());
//            if (demoEntity != null) {
//                demoRepository.delete(demoEntity);
//                return ResultUtil.success();
//            } else {
//                throw new WafException("", "xxx不存在", HttpStatus.NOT_ACCEPTABLE);
//            }
//        } else {
//            throw new WafException("", "xxx不存在", HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//    public JSONObject getAll(DemoListReq demoListReq) {
//        Pageable pageable = PageRequest.of(demoListReq.getPage() - 1, demoListReq.getSize());
//        Page<ExpertInfoEntity> demoEntities = demoRepository.findAll(
//                new Specification<ExpertInfoEntity>() {
//                    @Override
//                    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
//                        List<Predicate> predicates = new ArrayList<Predicate>();
//                        query.where(predicates.toArray(new Predicate[predicates.size()]));
//                        query.orderBy(cb.desc(root.<Date>get("createTime").as(Date.class)));
//                        return query.getRestriction();
//                    }
//                },
//                pageable);
//        return ResultUtil.pageSuccess(demoEntities.getContent(), demoEntities.getTotalElements());
//    }
}
