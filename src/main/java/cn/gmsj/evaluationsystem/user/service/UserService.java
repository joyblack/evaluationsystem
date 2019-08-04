package cn.gmsj.evaluationsystem.user.service;

import cn.gmsj.evaluationsystem.enums.UserDataType;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserRepository;
import cn.gmsj.evaluationsystem.utils.CheckIdNumberUtil;
import cn.gmsj.evaluationsystem.utils.MD5Util;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.StringUtil;
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
         * 判断短信注册码是否一致   --待验证
         */
        /**
         * 判断重复使用
         */
        UserEntity userEntity1 = null;
        userEntity1 = userRepository.findAllByPhoneAndIdNot(userEntity.getPhone(),userEntity.getId());
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
            if(!CheckIdNumberUtil.isIDNumber(userEntity.getIdNumber())){
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

}
