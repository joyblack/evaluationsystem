package cn.gmsj.evaluationsystem.login.service;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.config.JwtParamConfig;
import cn.gmsj.evaluationsystem.enums.Token;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.login.domain.model.LoginNote;
import cn.gmsj.evaluationsystem.login.web.req.LoginReq;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserRepository;
import cn.gmsj.evaluationsystem.utils.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 13562
 */
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginNoteRedisService loginNoteRedisService;

    @Autowired
    private JwtParamConfig jwtParamConfig;

    public JSONObject loginByPhone(LoginReq loginReq) {
        if (StringUtil.isEmpty(loginReq.getPhone())) {
            throw new WafException("", "手机号码不能为空", HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (!PhoneUtil.isMobile(loginReq.getPhone())) {
                throw new WafException("", "手机号码不合法", HttpStatus.NOT_ACCEPTABLE);
            }
            LoginNote loginNote = loginNoteRedisService.get(loginReq.getPhone());
            if (loginNote != null) {
                if (loginNote.getAuthCode().equals(loginReq.getAuthCode())) {
                    UserEntity userEntity = userRepository.findAllByPhone(loginNote.getPhone());
                    if (userEntity != null) {
                        loginNoteRedisService.remove(loginNote.getPhone());
                        Map<String, Object> claims = new HashMap<String, Object>();
                        claims.put(Token.USER.getName(), userEntity);
                        return ResultUtil.success(JwtUtil.createJWT(claims, jwtParamConfig));
                    } else {
                        throw new WafException("", "验证码错误", HttpStatus.NOT_ACCEPTABLE);
                    }
                } else {
                    throw new WafException("", "验证码错误", HttpStatus.NOT_ACCEPTABLE);
                }
            } else {
                throw new WafException("", "验证码错误", HttpStatus.NOT_ACCEPTABLE);
            }
        }
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
                return ResultUtil.success();
            } else {
                throw new WafException("", "该手机号尚未注册", HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }

    public JSONObject login(LoginReq loginReq) {
        if (StringUtil.isEmpty(loginReq.getUsername())) {
            throw new WafException("", "手机号/身份证/社会信用代码不能为空", HttpStatus.NOT_ACCEPTABLE);
        }
        if (StringUtil.isEmpty(loginReq.getPassword())) {
            throw new WafException("", "密码不能为空", HttpStatus.NOT_ACCEPTABLE);
        }
        UserEntity userEntity = userRepository.findAllByPhoneAndPassword(loginReq.getUsername(), MD5Util.encode(loginReq.getPassword()));
        if (userEntity != null) {
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put(Token.USER.getName(), userEntity);
            return ResultUtil.success(JwtUtil.createJWT(claims, jwtParamConfig));
        } else {
            userEntity = userRepository.findAllByIdNumberAndPassword(loginReq.getUsername(), MD5Util.encode(loginReq.getPassword()));
            if (userEntity != null) {
                Map<String, Object> claims = new HashMap<String, Object>();
                claims.put(Token.USER.getName(), userEntity);
                return ResultUtil.success(JwtUtil.createJWT(claims, jwtParamConfig));
            } else {
                userEntity = userRepository.findAllBySocialCreditCodeAndPassword(loginReq.getUsername(), MD5Util.encode(loginReq.getPassword()));
                if (userEntity != null) {
                    Map<String, Object> claims = new HashMap<String, Object>();
                    claims.put(Token.USER.getName(), userEntity);
                    return ResultUtil.success(JwtUtil.createJWT(claims, jwtParamConfig));
                } else {
                    throw new WafException("", "手机号/身份证/社会信用代码与密码不匹配", HttpStatus.NOT_ACCEPTABLE);
                }
            }
        }
    }
}
