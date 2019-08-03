package cn.gmsj.evaluationsystem.user.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.service.UserService;
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
}
