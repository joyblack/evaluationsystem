package cn.gmsj.evaluationsystem.user.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.user.domain.model.UserRegisterNote;
import cn.gmsj.evaluationsystem.user.service.UserRegisterNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13562
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/register-note")
public class UserRegisterNoteController {

    @Autowired
    private UserRegisterNoteService userRegisterNoteService;

    @PostMapping(
            value = "/sendNote",
            produces = {"application/json;charset=UTF-8"})
    public Object sendNote(@RequestBody UserRegisterNote userRegisterNote) {
        return userRegisterNoteService.sendNote(userRegisterNote);
    }

    @PostMapping(
            value = "/checkAuthCode",
            produces = {"application/json;charset=UTF-8"})
    public Object checkAuthCode(@RequestBody UserRegisterNote userRegisterNote) {
        return userRegisterNoteService.checkAuthCode(userRegisterNote);
    }


}
