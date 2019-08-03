package cn.gmsj.evaluationsystem.userreg.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.userreg.domain.entity.UserRegEntity;
import cn.gmsj.evaluationsystem.userreg.service.UserRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Administrator
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/user_reg")
public class UserRegController {
    @Autowired
    private UserRegService userRegService;

    @PostMapping(
            value = "/updateData",
            produces = {"application/json;charset=UTF-8"})
    public Object updateData(@RequestBody @Valid UserRegEntity userRegEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WafException(
                    "", bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else {
            return userRegService.updateData(userRegEntity);
        }
    }


}
