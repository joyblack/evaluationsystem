package cn.gmsj.evaluationsystem.examine.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.examine.domain.entity.ExamineEntity;
import cn.gmsj.evaluationsystem.examine.service.ExamineService;
import cn.gmsj.evaluationsystem.examine.web.req.ExamineListReq;
import cn.gmsj.evaluationsystem.examine.web.req.ExamineReq;
import cn.gmsj.evaluationsystem.exception.WafException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by XiaoWen on 2019/8/3
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/examine")
public class ExamineController {

    @Autowired
    private ExamineService examineService;

    @PostMapping(
            value = "/updateData",
            produces = {"application/json;charset=UTF-8"})
    public Object updateData(@RequestBody @Valid ExamineEntity examineEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WafException(
                    "", bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else {
            return examineService.updateData(examineEntity);
        }
    }

    @PostMapping(
            value = "/getById",
            produces = {"application/json;charset=UTF-8"})
    public Object getById(@RequestBody @Valid ExamineReq examineReq,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WafException(
                    "", bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else {
            return examineService.getById(examineReq);
        }
    }

    @PostMapping(
            value = "/getAll",
            produces = {"application/json;charset=UTF-8"})
    public Object getAll(@RequestBody ExamineListReq examineListReq, HttpServletRequest request) {
        return examineService.getAll(examineListReq,request);
    }


}
