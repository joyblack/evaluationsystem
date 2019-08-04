package cn.gmsj.evaluationsystem.expertdesign.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.expertdesign.domain.entity.ExamineEntity;
import cn.gmsj.evaluationsystem.expertdesign.domain.entity.InviteEntity;
import cn.gmsj.evaluationsystem.expertdesign.domain.repository.ExamineRepository;
import cn.gmsj.evaluationsystem.expertdesign.domain.repository.InviteRepository;
import cn.gmsj.evaluationsystem.expertdesign.web.req.ExamineReq;
import cn.gmsj.evaluationsystem.expertdesign.web.req.InviteListReq;
import cn.gmsj.evaluationsystem.expertdesign.web.req.InviteReq;
import cn.gmsj.evaluationsystem.invite.domain.entity.ProjectInviteEntity;

import cn.gmsj.evaluationsystem.projectmanage.domain.entity.ProjectEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserRepository;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.UpdateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 审查业务
 */
@Service
public class InviteService {
    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private UserRepository userRegRepository;

    @Autowired
    private ExamineRepository examineRepository;


    public JSONObject updateData(ExamineEntity examineEntity) {
        if (null == examineEntity.getId()) {
            examineEntity.setId(0L);
        } else {
            UpdateUtil.copyProperties(examineRepository.findAllById(examineEntity.getId()), examineEntity);
            examineEntity.setUpdateTime(new Date());
        }

        return ResultUtil.success(examineRepository.save(examineEntity));
    }
     public  JSONObject InquireProject(ExamineReq examineReq){
        if (examineReq.getProjectName()!=null){
            List<ExamineEntity> examineEntity=examineRepository.findAllByProject(examineReq.getProjectName());
            if (examineEntity!=null){
                return  ResultUtil.success(examineEntity);
            }else {
                return ResultUtil.error("你查找的项目不存在");
            }
        }else {
            return ResultUtil.error("你查找的项目不存在");
        }

     }


    public JSONObject InviteAccept(InviteReq ExperDesignReq) {
        if (ExperDesignReq.getId() != null) {
            InviteEntity inviteEntity = inviteRepository.findAllById(ExperDesignReq.getId());
            if (inviteEntity != null) {
                return ResultUtil.success(inviteEntity);
            } else {
                throw new WafException("", "邀请的不存在", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            throw new WafException("", "邀请的不存在", HttpStatus.NOT_ACCEPTABLE);
        }
    }
    public JSONObject InviteRefuse(InviteReq ExperDesignReq) {
        if (ExperDesignReq.getId() != null) {
            InviteEntity inviteEntity = inviteRepository.findAllById(ExperDesignReq.getId());
            if (inviteEntity != null) {
                return ResultUtil.success(inviteEntity);
            } else {
                throw new WafException("", "邀请的不存在", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            throw new WafException("", "邀请的不存在", HttpStatus.NOT_ACCEPTABLE);
        }
    }


    public JSONObject getAllByUserReg(InviteListReq inviteListReq) {
        if (inviteListReq.getUserRegId() != null) {
            UserEntity userRegEntity = userRegRepository.findAllById(inviteListReq.getUserRegId());
            if (userRegEntity != null) {
                Pageable pageable = PageRequest.of(inviteListReq.getPage() - 1, inviteListReq.getSize());
                Page<InviteEntity> inviteEntities = inviteRepository.findAllByUser(userRegEntity, pageable);
                return ResultUtil.pageSuccess(inviteEntities.getContent(), inviteEntities.getTotalElements());
            } else {
                throw new WafException("", "用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            throw new WafException("", "用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }


    }
}
