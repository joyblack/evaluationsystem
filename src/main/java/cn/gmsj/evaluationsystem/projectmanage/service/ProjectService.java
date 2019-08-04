package cn.gmsj.evaluationsystem.projectmanage.service;



import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.projectmanage.domain.entity.ProjectEntity;
import cn.gmsj.evaluationsystem.projectmanage.domain.repository.ProjectRepository;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.UpdateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;



    public JSONObject updateData(ProjectEntity projectEntity) {
        if (null == projectEntity.getId()) {
            projectEntity.setId(0L);
        } else {
            UpdateUtil.copyProperties(projectRepository.findAllById(projectEntity.getId()), projectEntity);
            projectEntity.setUpdateTime(new Date());
        }
        if(projectEntity.getProjectName()!=null && projectEntity.getId()!=null){

          ProjectEntity proe= projectRepository.findAllById(projectEntity.getId());
          if (proe.getProjectName().equals(projectEntity.getProjectName())){
              throw new WafException("", "您输入的项目名称已存在！", HttpStatus.NOT_ACCEPTABLE);
          }
          if(proe.getProjectPaperUrl().equals(projectEntity.getProjectPaperUrl())){
              return ResultUtil.error("您不能重复提交资料！");
          }


        }
        return ResultUtil.success(projectRepository.save(projectEntity));
    }
}
