package cn.gmsj.evaluationsystem.projectmanage.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.projectmanage.service.ProjectInviteService;
import cn.gmsj.evaluationsystem.projectmanage.web.req.ProjectInviteListReq;
import cn.gmsj.evaluationsystem.projectmanage.web.req.ProjectInviteReq;
import cn.gmsj.evaluationsystem.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 13562
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/project-invite")
public class ProjectInviteController {

    @Autowired
    private ProjectInviteService projectInviteService;

    /**
     * 接受邀请
     *
     * @param projectInviteReq
     * @return
     */
    @PostMapping(
            value = "/accept",
            produces = {"application/json;charset=UTF-8"})
    public Object accept(@RequestBody ProjectInviteReq projectInviteReq) {
        return projectInviteService.accept(projectInviteReq);
    }

    /**
     * 拒绝邀请
     *
     * @param projectInviteReq
     * @return
     */
    @PostMapping(
            value = "/refuse",
            produces = {"application/json;charset=UTF-8"})
    public Object refuse(@RequestBody ProjectInviteReq projectInviteReq) {
        return projectInviteService.refuse(projectInviteReq);
    }

    /**
     * 获取用户邀请记录
     *
     * @param projectInviteListReq
     * @return
     */
    @PostMapping(
            value = "/getAllByUser",
            produces = {"application/json;charset=UTF-8"})
    public Object getAllByUser(@RequestBody ProjectInviteListReq projectInviteListReq, HttpServletRequest request) {
        projectInviteListReq.setUserId(TokenUtil.getUserId(request));
        return projectInviteService.getAllByUser(projectInviteListReq);
    }

}
