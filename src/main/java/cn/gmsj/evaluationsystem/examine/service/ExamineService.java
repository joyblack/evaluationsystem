package cn.gmsj.evaluationsystem.examine.service;

import cn.gmsj.evaluationsystem.examine.domain.entity.ExamineEntity;
import cn.gmsj.evaluationsystem.examine.domain.repository.ExamineRepository;
import cn.gmsj.evaluationsystem.examine.web.req.ExamineListReq;
import cn.gmsj.evaluationsystem.examine.web.req.ExamineReq;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.invite.domain.entity.ProjectInviteEntity;
import cn.gmsj.evaluationsystem.invite.domain.repository.ProjectInviteRepository;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserRepository;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.StringUtil;
import cn.gmsj.evaluationsystem.utils.TokenUtil;
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
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by XiaoWen on 2019/8/3
 */
@Service
public class ExamineService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamineRepository examineRepository;

    @Autowired
    private ProjectInviteRepository projectInviteRepository;

    public JSONObject updateData(ExamineEntity examineEntity){
        if(null == examineEntity.getId()){
            examineEntity.setId(0L);
        }else{
            examineEntity.setUpdateTime(new Date());
        }
        if(null == examineEntity.getProjectInvite() || null == examineEntity.getId()){
            return ResultUtil.error("邀请记录为空");
        }
        ProjectInviteEntity projectInviteEntity = projectInviteRepository.findAllById(examineEntity.getProjectInvite().getId());
        if(null == projectInviteEntity){
            throw new WafException("","邀请记录信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        examineRepository.save(examineEntity);
        return ResultUtil.success();
    }

    public JSONObject getById(ExamineReq examineReq){
        ExamineEntity examineEntity = examineRepository.findAllById(examineReq.getId());
        if(null == examineEntity){
            throw new WafException("", "审查信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(examineEntity);
    }

    public JSONObject getAll(ExamineListReq examineListReq, HttpServletRequest request) {
        UserEntity userEntity = TokenUtil.getUser(request);
        if(null == userEntity || null == userEntity.getId()){
            throw new WafException("", "用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        UserEntity userEntity1 = userRepository.findAllById(userEntity.getId());
        if(null == userEntity1){
            throw new WafException("", "用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        Pageable pageable = PageRequest.of(examineListReq.getPage() - 1, examineListReq.getSize());
        Page<ExamineEntity> examineEntities = examineRepository.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(
                        cb.equal(root.<UserEntity>get("projectInvite").get("user"),
                                userEntity1));
                if (StringUtil.isNotEmpty(examineListReq.getProjectName())) {
                    predicates.add(
                            cb.like(root.<String> get("projectInvite").get("project").get("projectName"),
                                    "%" + examineListReq.getProjectName() + "%"));
                }
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                query.orderBy(cb.desc(root.<Date> get("createTime").as(Date.class)));
                return query.getRestriction();
            }
        }, pageable);
        return ResultUtil.pageSuccess(examineEntities.getContent(), examineEntities.getTotalElements());
    }


}
