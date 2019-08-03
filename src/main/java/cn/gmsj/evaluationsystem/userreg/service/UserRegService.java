package cn.gmsj.evaluationsystem.userreg.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;


import cn.gmsj.evaluationsystem.userreg.domain.entity.UserRegEntity;
import cn.gmsj.evaluationsystem.userreg.domain.repository.UserRegRepository;
import cn.gmsj.evaluationsystem.userreg.service.verify.IDCardValidate;
import cn.gmsj.evaluationsystem.userreg.service.verify.MessageCode;
import cn.gmsj.evaluationsystem.utils.MD5Util;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.UpdateUtil;
import org.springframework.stereotype.Service;



/**
 *
 */
@Service
public class UserRegService {
	@Autowired
	private UserRegRepository userRegRepository;
	
	
	public JSONObject updateData(UserRegEntity userRegEntity) {
		if (null == userRegEntity.getId()) {
			userRegEntity.setId(0L);
        } else {
            UpdateUtil.copyProperties(userRegRepository.findAllById(userRegEntity.getId()), userRegEntity);
            userRegEntity.setUpdateTime(new Date());
        }
		//1表示专家注册
		if ( userRegEntity.getUserDataType().getName().equals(userRegEntity.getUserDataType().getName())){
			if (userRegEntity.getNumberId()!=null){
				//身份证验证
				String numId=userRegEntity.getNumberId();
				//	IDCardValidate IDCard=new IDCardValidate ();
				IDCardValidate.chekIdCard(numId);
				userRegEntity.setNumberId(numId);
			}
		}

		//密码
		String password=userRegEntity.getPassword();
		String rePassword=userRegEntity.getRepassword();
		if(password.equals(rePassword)) {
		
			String MD5password=MD5Util.encode(password);
			userRegEntity.setPassword(MD5password);	
		}else {
			return ResultUtil.error("密码不一致！");
		}
	    //手机号验证
		String phone=userRegEntity.getPhone();
		String code=MessageCode.getCode(phone);
		if (userRegEntity.getCode().equals(code)){
			userRegEntity.setCode(code);

		}

		
		return ResultUtil.success(userRegRepository.save(userRegEntity));
		
	}

}
