package cn.gmsj.evaluationsystem.user.service;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.enums.UserDataType;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.domain.model.UserPassordResetNote;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserRepository;
import cn.gmsj.evaluationsystem.user.web.req.UserPasswordResetReq;
import cn.gmsj.evaluationsystem.utils.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by XiaoWen on 2019/8/3
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPasswordResetRedisService userPasswordResetRedisService;

    public JSONObject updateData(UserEntity userEntity){
        if(null == userEntity.getUserDataType()){
            return ResultUtil.error("用户类型为空");
        }
        if (null == userEntity.getId()) {
            userEntity.setId(0L);
        } else {
            throw new WafException("", "无效注册的操作", HttpStatus.NOT_ACCEPTABLE);
        }

        if(StringUtil.isEmpty(userEntity.getAffirmPassword())){
            return ResultUtil.error("确认密码为空");
        }

        if(!StringUtil.equals(userEntity.getPassword(),userEntity.getAffirmPassword())){
            throw new WafException("", "密码输入不一致", HttpStatus.NOT_ACCEPTABLE);
        }
        /**
         * 判断重复使用
         */
        UserEntity userEntity1 = null;
        userEntity1 = userRepository.findAllByPhoneAndIdNot(userEntity.getPhone(),userEntity.getId());
        if(null != userEntity1){
            throw new WafException("", "该手机号已被注册", HttpStatus.NOT_ACCEPTABLE);
        }
        if(StringUtil.equals(userEntity.getUserDataType().getName(), UserDataType.SPECIALIST.getName())){
            if(StringUtil.isEmpty(userEntity.getName())){
                return ResultUtil.error("姓名为空");
            }
            if(StringUtil.isEmpty(userEntity.getIdNumber())){
                return ResultUtil.error("身份证号为空");
            }
            /**
             * 验证身份证号
             */
            if(!IdNumberUtil.isIDNumber(userEntity.getIdNumber())){
                throw new WafException("", "身份证号错误", HttpStatus.NOT_ACCEPTABLE);
            }
            userEntity1 = userRepository.findAllByIdNumberAndIdNot(userEntity.getIdNumber(),userEntity.getId());
            if(null != userEntity1){
                throw new WafException("", "身份证号重复", HttpStatus.NOT_ACCEPTABLE);
            }
            userEntity.setPassword(MD5Util.encode(userEntity.getPassword()));
            userEntity.setBusinessLicence(null);
            userEntity.setSocialCreditCode(null);
            userEntity.setUnitName(null);
            UserEntity userEntity2 = userRepository.save(userEntity);
            userEntity2.setPassword(null);
            return ResultUtil.success(userEntity2);
        }else if(StringUtil.equals(userEntity.getUserDataType().getName(), UserDataType.THIRD_PARTY.getName())){
            if(StringUtil.isEmpty(userEntity.getUnitName())){
                return ResultUtil.error("单位全称为空");
            }
            if(StringUtil.isEmpty(userEntity.getSocialCreditCode())){
                return ResultUtil.error("统一社会信用代码为空");
            }
            /**
             * 营业执照的判断
             */
            userEntity1 = userRepository.findAllByUnitNameAndIdNot(userEntity.getUnitName(),userEntity.getId());
            if(null != userEntity1){
                throw new WafException("", "单位全称重复", HttpStatus.NOT_ACCEPTABLE);
            }
            userEntity1 = userRepository.findAllBySocialCreditCodeAndIdNot(userEntity.getSocialCreditCode(),userEntity.getId());
            if(null != userEntity1){
                throw new WafException("", "统一社会信用代码重复", HttpStatus.NOT_ACCEPTABLE);
            }
            userEntity.setPassword(MD5Util.encode(userEntity.getPassword()));
            userEntity.setName(null);
            userEntity.setIdNumber(null);
            UserEntity userEntity2 = userRepository.save(userEntity);
            userEntity2.setPassword(null);
            return ResultUtil.success(userEntity2);
        }else{
            return ResultUtil.error("无效的注册操作");
        }
    }


    public JSONObject checkPhone(String phone){
        UserEntity userEntity = userRepository.findAllByPhone(phone);
        if(null == userEntity){
            return ResultUtil.success();
        }else{
            return ResultUtil.error("该手机号已被注册");
        }
    }

    public JSONObject checkSocialCreditCod(String socialCreditCode){
        UserEntity userEntity = userRepository.findAllBySocialCreditCode(socialCreditCode);
        if(null == userEntity){
            return ResultUtil.success();
        }else{
            return ResultUtil.error("该社会信用代码已被注册");
        }
    }

    public JSONObject checkIdNumber(String idNumber){
        UserEntity userEntity = userRepository.findAllByIdNumber(idNumber);
        if(null == userEntity){
            return ResultUtil.success();
        }else{
            return ResultUtil.error("该身份证号已被注册");
        }
    }

    public JSONObject sendResetPasswordNote(UserPasswordResetReq userPasswordResetReq) {
        if (StringUtil.isEmpty(userPasswordResetReq.getPhone())) {
            throw new WafException("", "手机号码不能为空", HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (!PhoneUtil.isMobile(userPasswordResetReq.getPhone())) {
                throw new WafException("", "手机号码不合法", HttpStatus.NOT_ACCEPTABLE);
            }
            if (userRepository.findAllByPhone(userPasswordResetReq.getPhone()) != null) {
                UserPassordResetNote userPassordResetNote = new UserPassordResetNote();
                userPassordResetNote.setPhone(userPasswordResetReq.getPhone());
                userPassordResetNote.setAuthCode(RandomUtil.sixAuthCode());
                userPasswordResetRedisService.put(userPasswordResetReq.getPhone(), userPassordResetNote, SystemConstant.NOTE_VALID_TIME);
                return ResultUtil.success();
            } else {
                throw new WafException("", "该手机号尚未注册", HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }

    public JSONObject resetPasswordByPhone(UserPasswordResetReq userPasswordResetReq) {
        if (StringUtil.isEmpty(userPasswordResetReq.getPhone())) {
            throw new WafException("", "手机号码不能为空", HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (!PhoneUtil.isMobile(userPasswordResetReq.getPhone())) {
                throw new WafException("", "手机号码不合法", HttpStatus.NOT_ACCEPTABLE);
            }
            UserPassordResetNote userPassordResetNote = userPasswordResetRedisService.get(userPasswordResetReq.getPhone());
            if (userPassordResetNote != null) {
                if (userPassordResetNote.getAuthCode().equals(userPasswordResetReq.getAuthCode())) {
                    UserEntity userEntity = userRepository.findAllByPhone(userPasswordResetReq.getPhone());
                    if (userEntity != null) {
                        userPasswordResetRedisService.remove(userPassordResetNote.getPhone());
                        if(StringUtil.isEmpty(userPasswordResetReq.getPassword())){
                            throw new WafException("", "密码为空", HttpStatus.NOT_ACCEPTABLE);
                        }
                        if(StringUtil.isEmpty(userPasswordResetReq.getAffirmPassword())){
                            throw new WafException("", "确认密码为空", HttpStatus.NOT_ACCEPTABLE);
                        }
                        if(!StringUtil.equals(userPasswordResetReq.getPassword(),userPasswordResetReq.getAffirmPassword())){
                            throw new WafException("", "密码输入不一致", HttpStatus.NOT_ACCEPTABLE);
                        }
                        userEntity.setPassword(MD5Util.encode(userPasswordResetReq.getPassword()));
                        userRepository.save(userEntity);
                        return ResultUtil.success();
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
}
