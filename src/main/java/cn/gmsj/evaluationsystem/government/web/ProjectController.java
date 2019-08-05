package cn.gmsj.evaluationsystem.government.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.government.domain.entity.ProjectEntity;
import cn.gmsj.evaluationsystem.government.service.ProjectService;
import cn.gmsj.evaluationsystem.government.web.req.ProjectListReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 项目的控制层
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/add_project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @PostMapping(
            value = "/updateData",
            produces = {"application/json;charset=UTF-8"})
    public Object updateData(@RequestBody @Valid ProjectEntity projectEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WafException(
                    "", bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else {
            return projectService.updateData(projectEntity);
        }
    }
    @PostMapping(
            value = "/getProjectName",
            produces = {"application/json;charset=UTF-8"})
    public  Object getProjectName(@RequestBody ProjectListReq projectListReq){
        return  projectService.getOneProject(projectListReq);


    }
}
