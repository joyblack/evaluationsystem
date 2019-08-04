package cn.gmsj.evaluationsystem.login.service;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.login.domain.model.LoginNote;
import cn.gmsj.evaluationsystem.login.web.req.LoginReq;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserRepository;
import cn.gmsj.evaluationsystem.utils.PhoneUtil;
import cn.gmsj.evaluationsystem.utils.RandomUtil;
import cn.gmsj.evaluationsystem.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author 13562
 */
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginNoteRedisService loginNoteRedisService;

    public JSONObject loginByPhone(LoginReq loginReq) {
        return null;
    }

    public JSONObject sendLoginNote(LoginReq loginReq) {
        if (StringUtil.isEmpty(loginReq.getPhone())) {
            throw new WafException("", "手机号码不能为空", HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (!PhoneUtil.isMobile(loginReq.getPhone())) {
                throw new WafException("", "手机号码不合法", HttpStatus.NOT_ACCEPTABLE);
            }
            if (userRepository.findAllByPhone(loginReq.getPhone()) != null) {
                LoginNote loginNote = new LoginNote();
                loginNote.setPhone(loginReq.getPhone());
                loginNote.setAuthCode(RandomUtil.sixAuthCode());
                loginNoteRedisService.put(loginNote.getPhone(), loginNote, SystemConstant.NOTE_VALID_TIME);
            } else {
                throw new WafException("", "该手机号尚未注册", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return null;
    }
}
