package cn.gmsj.evaluationsystem.projectmanage.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.projectmanage.domain.entity.ProjectEntity;
import cn.gmsj.evaluationsystem.projectmanage.service.ProjectService;
import cn.gmsj.evaluationsystem.userreg.domain.entity.UserRegEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
}
