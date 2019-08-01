package cn.gmsj.evaluationsystem.expertinfo.web;
import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.expertinfo.web.req.ExpertInfoReq;
import cn.gmsj.evaluationsystem.demo.web.req.DemoListReq;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.ExpertInfoEntity;
import cn.gmsj.evaluationsystem.expertinfo.service.ExpertInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//    @PostMapping(
//            value = "/getDataById",
//            produces = {"application/json;charset=UTF-8"})
//    public Object getDataById(@RequestBody ExpertInfoReq expertInfoReq) {
//        return expertInfoService.getDataById(expertInfoReq);
//    }
//
//    @PostMapping(
//            value = "/deleteDataById",
//            produces = {"application/json;charset=UTF-8"})
//    public Object deleteDataById(@RequestBody ExpertInfoReq demoReq) {
//        return expertInfoService.deleteDataById(demoReq);
//    }
//
//    @PostMapping(
//            value = "/getAll",
//            produces = {"application/json;charset=UTF-8"})
//    public Object getAll() {
//        return getAll();
//    }
//
//    @PostMapping(
//            value = "/getAll",
//            produces = {"application/json;charset=UTF-8"})
//    public Object getAll(@RequestBody DemoListReq demoListReq) {
//        return expertInfoService.getAll(demoListReq);
//    }
}
