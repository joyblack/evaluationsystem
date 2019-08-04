package cn.gmsj.evaluationsystem.user.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.domain.model.UserCheck;
import cn.gmsj.evaluationsystem.user.service.UserService;
import cn.gmsj.evaluationsystem.user.web.req.UserPasswordResetReq;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by XiaoWen on 2019/8/3
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param userEntity
     * @param bindingResult
     * @return
     */
    @PostMapping(
            value = "/updateData",
            produces = {"application/json;charset=UTF-8"})
    public Object updateData(@RequestBody @Valid UserEntity userEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WafException(
                    "", bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else {
            return userService.updateData(userEntity);
        }
    }

    /**
     * 检查手机号是否重复
     * @param userCheck
     * @return
     */
    @PostMapping(
            value = "/checkPhone",
            produces = {"application/json;charset=UTF-8"})
    public Object checkPhone(@RequestBody UserCheck userCheck) {
        if(StringUtil.isEmpty(userCheck.getPhone())){
            return ResultUtil.error("手机号为空");
        }
        return userService.checkPhone(userCheck.getPhone());
    }

    /**
     * 检查身份证号是否重复
     * @param userCheck
     * @return
     */
    @PostMapping(
            value = "/checkIdNumber",
            produces = {"application/json;charset=UTF-8"})
    public Object check(@RequestBody UserCheck userCheck) {
        if(StringUtil.isEmpty(userCheck.getIdNumber())){
            return ResultUtil.error("身份证号为空");
        }
        return userService.checkIdNumber(userCheck.getIdNumber());
    }

    /**
     * 检查社会信用代码
     * @param userCheck
     * @return
     */
    @PostMapping(
            value = "/checkSocialCreditCod",
            produces = {"application/json;charset=UTF-8"})
    public Object checkSocialCreditCod(@RequestBody UserCheck userCheck) {
        if(StringUtil.isEmpty(userCheck.getSocialCreditCode())){
            return ResultUtil.error("社会信用代码为空");
        }
        return userService.checkSocialCreditCod(userCheck.getSocialCreditCode());
    }

    /**
     * 重置密码--发送短信验证码
     * @param userPasswordResetReq
     * @return
     */
    @PostMapping(
            value = "/sendResetPasswordNote",
            produces = {"application/json;charset=UTF-8"})
    public Object sendResetPasswordNote(@RequestBody UserPasswordResetReq userPasswordResetReq) {
        return userService.sendResetPasswordNote(userPasswordResetReq);
    }

    /**
     * 重置密码
     * @param userPasswordResetReq
     * @return
     */
    @PostMapping(
            value = "/resetResetPassword",
            produces = {"application/json;charset=UTF-8"})
    public Object sendResetPassword(@RequestBody UserPasswordResetReq userPasswordResetReq) {
        return userService.resetPasswordByPhone(userPasswordResetReq);
    }

}
