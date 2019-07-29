package cn.gmsj.evaluationsystem.demo.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.demo.domain.entity.DemoEntity;
import cn.gmsj.evaluationsystem.demo.service.DemoService;
import cn.gmsj.evaluationsystem.demo.web.req.DemoListReq;
import cn.gmsj.evaluationsystem.demo.web.req.DemoReq;
import cn.gmsj.evaluationsystem.exception.WafException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 13562
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping(
            value = "/updateData",
            produces = {"application/json;charset=UTF-8"})
    public Object updateData(@RequestBody @Valid DemoEntity demoEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WafException(
                    "", bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else {
            return demoService.updateData(demoEntity);
        }
    }

    @PostMapping(
            value = "/getDataById",
            produces = {"application/json;charset=UTF-8"})
    public Object getDataById(@RequestBody DemoReq demoReq) {
        return demoService.getDataById(demoReq);
    }

    @PostMapping(
            value = "/deleteDataById",
            produces = {"application/json;charset=UTF-8"})
    public Object deleteDataById(@RequestBody DemoReq demoReq) {
        return demoService.deleteDataById(demoReq);
    }

    @PostMapping(
            value = "/getAll",
            produces = {"application/json;charset=UTF-8"})
    public Object getAll(@RequestBody DemoListReq demoListReq) {
        return demoService.getAll(demoListReq);
    }
}
