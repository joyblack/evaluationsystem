package cn.gmsj.evaluationsystem.expertverify.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.ExpertInfoEntity;
import cn.gmsj.evaluationsystem.expertinfo.service.ExpertInfoService;
import cn.gmsj.evaluationsystem.expertverify.service.ExpertVerifyService;
import cn.gmsj.evaluationsystem.expertverify.web.req.ExpertVerifyListReq;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/expertverify")
public class ExpertVerifyController {

    @Autowired
    private ExpertVerifyService expertVerifyService;
    /**
     * 专家审核--获取专家列表信息
     */
    @PostMapping(
            value = "/getExpertVerifyList",
            produces = {"application/json;charset=UTF-8"})
    public Object getExpertVerifyList(@RequestBody ExpertVerifyListReq expertVerifyListReq, HttpServletRequest req) {
        UserEntity userEntity= TokenUtil.getUser(req);
        if(userEntity==null){
            throw new WafException("","用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        return expertVerifyService.getExpertVerifyList(userEntity,expertVerifyListReq);
    }
}
