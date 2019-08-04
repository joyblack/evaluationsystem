package cn.gmsj.evaluationsystem.login.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.login.service.LoginService;
import cn.gmsj.evaluationsystem.login.web.req.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13562
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(
            value = "/sendNote",
            produces = {"application/json;charset=UTF-8"})
    public Object sendNote(@RequestBody LoginReq loginReq) {
        return loginService.sendLoginNote(loginReq);
    }

    @PostMapping(
            value = "/loginByPhone",
            produces = {"application/json;charset=UTF-8"})
    public Object loginByPhone(@RequestBody LoginReq loginReq) {
        return loginService.loginByPhone(loginReq);
    }
}
