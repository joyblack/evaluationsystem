package cn.gmsj.evaluationsystem.thirdparty.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.enums.UserDataType;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.ThirdPartyEntity;
import cn.gmsj.evaluationsystem.thirdparty.service.ThirdPartyService;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/thirdpart")
public class ThirdPartyController {

    @Autowired
    private ThirdPartyService thirdPartyService;

    /**
     * 通过当前用户，获取第三方机构信息
     */
    @RequestMapping(value = "/getThirdParty")
    public Object getThirdParty(HttpServletRequest req){
        UserEntity userEntity = TokenUtil.getUser(req);
        if(userEntity==null){
            throw new WafException("","用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        return thirdPartyService.getThirdParty(userEntity);
    }

    /**
     * 更新第三方机构信息
     */
    @PostMapping(value = "/updateThirdParty", produces = "application/json;charset=UTF-8")
    public Object updateThirdParty(@RequestBody @Valid ThirdPartyEntity thirdPartyEntity, BindingResult bindingResult, HttpServletRequest req){
        UserEntity userEntity = TokenUtil.getUser(req);
        if(userEntity==null){
            throw new WafException("","用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        if(!userEntity.getUserDataType().equals(UserDataType.THIRD_PARTY)){
            throw new WafException("","非法访问，不是第三方机构用户", HttpStatus.NOT_ACCEPTABLE);
        }
        if (bindingResult.hasErrors()) {
            throw new WafException(
                    "", bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else {
            System.out.printf("验证成功: " + thirdPartyEntity);
            return thirdPartyService.updateThirdParty(thirdPartyEntity,userEntity);
        }
    }






}
