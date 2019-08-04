package cn.gmsj.evaluationsystem.expertinfo.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.ExpertInfoEntity;
import cn.gmsj.evaluationsystem.expertinfo.service.ExpertInfoService;
import cn.gmsj.evaluationsystem.expertinfo.web.req.ExpertInfoReq;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 13562
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/expertinfo")
public class ExpertInfoController {

    @Autowired
    private ExpertInfoService expertInfoService;

    @PostMapping(
            value = "/updateData",
            produces = {"application/json;charset=UTF-8"})
    public Object updateData(@RequestBody ExpertInfoEntity expertInfoEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WafException(
                    "", bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else {
            return expertInfoService.updateData(expertInfoEntity);
        }
    }

    /**
     * 获取专家基本信息
     */
    @PostMapping(
            value = "/getExpertInfo",
            produces = {"application/json;charset=UTF-8"})
    public Object getExpertInfo(@RequestBody ExpertInfoReq expertInfoReq, HttpServletRequest req) {
        UserEntity userEntity= TokenUtil.getUser(req);
//        userEntity=new UserEntity();
//        userEntity.setIdNumber("522628199407125811");
//        userEntity.setName("周荣");
//        userEntity.setPhone("13595117402");
        if(userEntity==null){
            throw new WafException("","用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        return expertInfoService.getExpertInfo(expertInfoReq,userEntity);
    }


    /**
     * 获取所有职业类型列表
     */
    @GetMapping(
            value = "/getPositionalTypeList",
            produces = {"application/json;charset=UTF-8"})
    public Object getPositionalTypeList() {
        return expertInfoService.getPositionalTypeList();
    }

    /**
     * 获取所有职业名称列表
     */
    @GetMapping(
            value = "/getPositionalTitleList",
            produces = {"application/json;charset=UTF-8"})
    public Object getPositionalTitleList() {
        return expertInfoService.getPositionalTitleList();
    }

    /**
     * 获取最高学历列表
     */
    @GetMapping(
            value = "/getEducationList",
            produces = {"application/json;charset=UTF-8"})
    public Object getEducationList() {
        return expertInfoService.getEducationList();
    }

    /**
     * 获取所学专业列表
     */
    @GetMapping(
            value = "/getStudyMajorList",
            produces = {"application/json;charset=UTF-8"})
    public Object getStudyMajorList() {
        return expertInfoService.getStudyMajorList();
    }

    /**
     * 获取从事专业列表
     */
    @GetMapping(
            value = "/getEngagedMajorList",
            produces = {"application/json;charset=UTF-8"})
    public Object getEngagedMajorList() {
        return expertInfoService.getEngagedMajorList();
    }

    /**
     * 获取申报专业列表
     */
    @GetMapping(
            value = "/getDeclareMajorList",
            produces = {"application/json;charset=UTF-8"})
    public Object getDeclareMajorList() {
        return expertInfoService.getDeclareMajorList();
    }
}
