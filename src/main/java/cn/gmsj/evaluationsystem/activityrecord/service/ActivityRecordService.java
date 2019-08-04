package cn.gmsj.evaluationsystem.activityrecord.service;

import cn.gmsj.evaluationsystem.activityrecord.domain.entity.ActivityRecordEntity;
import cn.gmsj.evaluationsystem.activityrecord.domain.repository.ActivityRecordRepository;
import cn.gmsj.evaluationsystem.activityrecord.web.req.ActivityRecordListReq;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserRepository;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.TokenUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
public class ActivityRecordService {

    @Autowired
    private ActivityRecordRepository activityRecordRepository;

    @Autowired
    private UserRepository userRepository;

    public JSONObject getAllByUser(ActivityRecordListReq activityRecordListReq, HttpServletRequest request) {
        Pageable pageable = null;
        pageable = PageRequest.of(activityRecordListReq.getPage() - 1, activityRecordListReq.getSize());
        //获取登录用户信息
        UserEntity userEntity = TokenUtil.getUser(request);
        List<ActivityRecordEntity> activityRecordEntities = new ArrayList<>();
        if (null == userEntity) {
            return ResultUtil.pageSuccess(activityRecordEntities, activityRecordEntities.size());
        }
        UserEntity userEntity1 = userRepository.findAllById(userEntity.getId());
        if (null == userEntity1) {
            return ResultUtil.pageSuccess(activityRecordEntities, activityRecordEntities.size());
        }
        Page<ActivityRecordEntity> activityRecordEntities1 = activityRecordRepository.findAll(
                new Specification() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                        List<Predicate> predicates = new ArrayList<Predicate>();
                        predicates.add(cb.equal(root.<UserEntity>get("userEntity"), userEntity));
                        query.where(predicates.toArray(new Predicate[predicates.size()]));
                        query.orderBy(cb.desc(root.<Date>get("createTime").as(Date.class)));
                        return query.getRestriction();
                    }
                }, pageable);
        return ResultUtil.pageSuccess(activityRecordEntities1.getContent(), activityRecordEntities1.getTotalElements());
    }




}
