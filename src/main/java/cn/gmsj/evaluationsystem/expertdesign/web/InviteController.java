package cn.gmsj.evaluationsystem.expertdesign.web;


import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.expertdesign.service.InviteService;
import cn.gmsj.evaluationsystem.expertdesign.web.req.ExamineReq;
import cn.gmsj.evaluationsystem.expertdesign.web.req.InviteListReq;
import cn.gmsj.evaluationsystem.expertdesign.web.req.InviteReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = SystemConstant.API_VERSION + "/invite")
public class InviteController {
    @Autowired
    private InviteService inviteService;

    @PostMapping(
            value = "/accpt",
            produces = {"application/json;charset=UTF-8"})
    public Object InviteAccept(@RequestBody InviteReq inviteReq) {

        return inviteService.InviteAccept(inviteReq);
    }
    @PostMapping(
            value = "/refuse",
            produces = {"application/json;charset=UTF-8"})
    public Object InviteRefuse(@RequestBody InviteReq inviteReq) {

        return inviteService.InviteRefuse(inviteReq);
    }
    @PostMapping(
            value = "/getAllByUserReg",
            produces = {"application/json;charset=UTF-8"})
    public Object getAllByUserReg(@RequestBody InviteListReq inviteListReq) {
        return inviteService.getAllByUserReg(inviteListReq);


    }
    @PostMapping(
            value = "/getInquireProject",
            produces = {"application/json;charset=UTF-8"})
    public Object getInquireProject(@RequestBody ExamineReq examineReq){
        return inviteService.InquireProject(examineReq);
    }
}
