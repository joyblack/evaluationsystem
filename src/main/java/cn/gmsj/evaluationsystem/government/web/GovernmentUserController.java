package cn.gmsj.evaluationsystem.government.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.government.domain.entity.GovernmentUserEntity;
import cn.gmsj.evaluationsystem.government.service.GovernmentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by XiaoWen on 2019/8/4
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/government-user")
public class GovernmentUserController {

    @Autowired
    private GovernmentUserService governmentUserService;

    @PostMapping(
            value = "/updateData",
            produces = {"application/json;charset=UTF-8"})
    public Object updateData(@RequestBody @Valid GovernmentUserEntity governmentUserEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WafException(
                    "", bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else {
            return governmentUserService.updateData(governmentUserEntity);
        }
    }

}
