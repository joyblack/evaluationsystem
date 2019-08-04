package cn.gmsj.evaluationsystem.government.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.government.domain.entity.GovernmentUserEntity;
import cn.gmsj.evaluationsystem.government.domain.repository.GovernmentUserRepository;
import cn.gmsj.evaluationsystem.utils.MD5Util;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by XiaoWen on 2019/8/4
 */
@Service
public class GovernmentUserService {

    @Autowired
    private GovernmentUserRepository governmentUserRepository;

    public JSONObject updateData(GovernmentUserEntity governmentUserEntity){
        if(StringUtil.isEmpty(governmentUserEntity.getAffirmPassword())){
            throw new WafException("","确认密码不能为空", HttpStatus.NOT_ACCEPTABLE);
        }
        GovernmentUserEntity governmentUserEntity1 = null;
        governmentUserEntity1 = governmentUserRepository.findAllByNameAndIdNot(governmentUserEntity.getName(),governmentUserEntity1.getId());
        if(null != governmentUserEntity1){
            throw new WafException("","姓名重复", HttpStatus.NOT_ACCEPTABLE);
        }
        governmentUserEntity1 = governmentUserRepository.findAllByPhoneAndIdNot(governmentUserEntity.getPhone(),governmentUserEntity1.getId());
        if(null != governmentUserEntity1){
            throw new WafException("","手机号重复", HttpStatus.NOT_ACCEPTABLE);
        }
        if(StringUtil.equals(governmentUserEntity.getPassword(),governmentUserEntity.getAffirmPassword())){
            throw new WafException("","密码不一致", HttpStatus.NOT_ACCEPTABLE);
        }
        governmentUserEntity.setPassword(MD5Util.encode(governmentUserEntity.getPassword()));
        GovernmentUserEntity governmentUserEntity2 = governmentUserRepository.save(governmentUserEntity);
        governmentUserEntity2.setPassword(null);
        return ResultUtil.success(governmentUserEntity2);
    }

}
