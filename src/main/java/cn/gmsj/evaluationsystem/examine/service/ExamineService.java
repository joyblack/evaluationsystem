package cn.gmsj.evaluationsystem.examine.service;

import cn.gmsj.evaluationsystem.examine.domain.entity.ExamineEntity;
import cn.gmsj.evaluationsystem.examine.domain.repository.ExamineRepository;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.invite.domain.entity.ProjectInviteEntity;
import cn.gmsj.evaluationsystem.invite.domain.repository.ProjectInviteRepository;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by XiaoWen on 2019/8/3
 */
@Service
public class ExamineService {

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


}
