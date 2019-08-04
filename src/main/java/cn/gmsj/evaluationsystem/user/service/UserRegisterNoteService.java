package cn.gmsj.evaluationsystem.user.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.user.domain.model.UserRegisterNote;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserRepository;
import cn.gmsj.evaluationsystem.utils.PhoneUtil;
import cn.gmsj.evaluationsystem.utils.RandomUtil;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author 13562
 */
@Service
public class UserRegisterNoteService {

    @Autowired
    private UserRegisterNoteRedisService userRegisterNoteRedisService;

    @Autowired
    private UserRepository userRepository;


    public JSONObject sendNote(UserRegisterNote userRegisterNote) {
        if (StringUtil.isEmpty(userRegisterNote.getPhone())) {
            throw new WafException("", "手机号码不能为空", HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (!PhoneUtil.isMobile(userRegisterNote.getPhone())) {
                throw new WafException("", "手机号码不合法", HttpStatus.NOT_ACCEPTABLE);
            }
            if (userRepository.findAllByPhone(userRegisterNote.getPhone()) != null) {
                throw new WafException("", "该手机号已被注册", HttpStatus.NOT_ACCEPTABLE);
            }
            userRegisterNote.setAuthCode(RandomUtil.sixAuthCode());
            userRegisterNoteRedisService.put(userRegisterNote.getPhone(), userRegisterNote, 1800);
            return ResultUtil.success();
        }
    }

    public JSONObject checkAuthCode(UserRegisterNote userRegisterNote) {
        if (StringUtil.isEmpty(userRegisterNote.getPhone())) {
            throw new WafException("", "手机号码不能为空", HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (!PhoneUtil.isMobile(userRegisterNote.getPhone())) {
                throw new WafException("", "手机号码不合法", HttpStatus.NOT_ACCEPTABLE);
            }
            UserRegisterNote userRegisterNote1 = userRegisterNoteRedisService.get(userRegisterNote.getPhone());
            if (userRegisterNote1 != null) {
                if (userRegisterNote1.getAuthCode().equals(userRegisterNote.getAuthCode())) {
                    userRegisterNoteRedisService.remove(userRegisterNote.getPhone());
                    return ResultUtil.success();
                } else {
                    throw new WafException("", "验证码错误", HttpStatus.NOT_ACCEPTABLE);
                }
            } else {
                throw new WafException("", "验证码错误", HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }
}
