package cn.gmsj.evaluationsystem.expertdesign.web;

import cn.gmsj.evaluationsystem.demo.web.req.DemoReq;
import cn.gmsj.evaluationsystem.expertdesign.service.ExperDesignService;
import cn.gmsj.evaluationsystem.expertdesign.web.req.ExperDesignReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ExperDesignController {
    @Autowired
    private ExperDesignService experDesignService;
    @PostMapping(
            value = "/getDataById",
            produces = {"application/json;charset=UTF-8"})
    public Object getDataById(@RequestBody ExperDesignReq experDesignReq) {

        return experDesignService.getDataById(experDesignReq);
    }
}
