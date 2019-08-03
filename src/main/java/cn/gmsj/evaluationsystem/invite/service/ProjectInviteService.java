package cn.gmsj.evaluationsystem.invite.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.invite.domain.entity.ProjectInviteEntity;
import cn.gmsj.evaluationsystem.invite.domain.repository.ProjectInviteRepository;
import cn.gmsj.evaluationsystem.invite.enums.InviteState;
import cn.gmsj.evaluationsystem.invite.web.req.ProjectInviteListReq;
import cn.gmsj.evaluationsystem.invite.web.req.ProjectInviteReq;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserRepository;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author 13562
 */
@Service
public class ProjectInviteService {


    @Autowired
    private ProjectInviteRepository projectInviteRepository;


    @Autowired
    private UserRepository userRepository;

    public JSONObject accept(ProjectInviteReq projectInviteReq) {
        if (projectInviteReq.getId() != null) {
            ProjectInviteEntity projectInviteEntity = projectInviteRepository.findAllById(projectInviteReq.getId());
            if (projectInviteEntity != null) {
                projectInviteEntity.setInviteState(InviteState.ACCEPT);
                return ResultUtil.success();
            } else {
                throw new WafException("", "邀请信息不存在", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            throw new WafException("", "邀请信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public JSONObject refuse(ProjectInviteReq projectInviteReq) {
        if (projectInviteReq.getId() != null) {
            ProjectInviteEntity projectInviteEntity = projectInviteRepository.findAllById(projectInviteReq.getId());
            if (projectInviteEntity != null) {
                projectInviteEntity.setInviteState(InviteState.REFUSE);
                return ResultUtil.success();
            } else {
                throw new WafException("", "邀请信息不存在", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            throw new WafException("", "邀请信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public JSONObject getAllByUser(ProjectInviteListReq projectInviteListReq) {
        if (projectInviteListReq.getUserId() != null) {
            UserEntity userEntity = userRepository.findAllById(projectInviteListReq.getUserId());
            if (userEntity != null) {
                Pageable pageable = PageRequest.of(projectInviteListReq.getPage() - 1, projectInviteListReq.getSize());
                Page<ProjectInviteEntity> projectInviteEntities = projectInviteRepository.findAllByUser(userEntity, pageable);
                return ResultUtil.pageSuccess(projectInviteEntities.getContent(), projectInviteEntities.getTotalElements());
            } else {
                throw new WafException("", "用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            throw new WafException("", "用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }

    }
}