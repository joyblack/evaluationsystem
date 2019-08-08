package cn.gmsj.evaluationsystem.thirdparty.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.specialist.domain.entity.*;
import cn.gmsj.evaluationsystem.specialist.web.res.DeclareMajorArrayRes;
import cn.gmsj.evaluationsystem.specialist.web.res.ExpertInfoRes;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.ThirdPartyEntity;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.ThirdPartyStaffEntity;
import cn.gmsj.evaluationsystem.thirdparty.domain.repository.ThirdPartyRepository;
import cn.gmsj.evaluationsystem.thirdparty.domain.repository.ThirdPartyStaffRepository;
import cn.gmsj.evaluationsystem.thirdparty.web.res.ThirdPartyRes;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.utils.IdNumberUtil;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.UpdateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class ThirdPartyService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private ThirdPartyStaffRepository thirdPartyStaffRepository;

    public JSONObject getThirdParty(UserEntity userEntity) {
        ThirdPartyEntity thirdPartyEntity = thirdPartyRepository.findAllByUserId(userEntity.getId());
        ThirdPartyRes res = new ThirdPartyRes();
        if(thirdPartyEntity == null){
            res.setCompanyName(userEntity.getUnitName());
            res.setSocialCreditCode(userEntity.getSocialCreditCode());
        }else{
            res.setSocialCreditCode(thirdPartyEntity.getSocialCreditCode());
            res.setCompanyName(thirdPartyEntity.getCompanyName());
            res.setCompanyMailbox(thirdPartyEntity.getCompanyMailbox());
            res.setArea(thirdPartyEntity.getArea());
            res.setCompanyAddress(thirdPartyEntity.getCompanyAddress());
            res.setCompanyTelephone(thirdPartyEntity.getArea());
            res.setCounty(thirdPartyEntity.getCounty());
            res.setLegalRepresent(thirdPartyEntity.getLegalRepresent());
            res.setLegalRepresentPhone(thirdPartyEntity.getLegalRepresentPhone());
            res.setPhone(thirdPartyEntity.getPhone());
            res.setPostalCode(thirdPartyEntity.getPostalCode());
            res.setProvince(thirdPartyEntity.getProvince());
            res.setRelatedWorkAchievement(thirdPartyEntity.getRelatedWorkAchievement());
        }
        return ResultUtil.success(res);
    }

    public JSONObject updateThirdParty(ThirdPartyEntity thirdPartyEntity, UserEntity userEntity) {
        // 添加设置为0，修改设置为该记录的ID
        if(null == thirdPartyEntity.getId()){
            thirdPartyEntity.setId(0L);
            thirdPartyEntity.setUserId(userEntity.getId());
        }else{
            // copy from database
            UpdateUtil.copyProperties(thirdPartyRepository.findAllById(thirdPartyEntity.getId()),thirdPartyEntity);
            // set update time
            thirdPartyEntity.setUpdateTime(new Date());
        }
        System.out.println(thirdPartyEntity.getId());
        List<ThirdPartyEntity> allByPhoneAndIdNot = thirdPartyRepository.findAllByPhoneAndIdNot(thirdPartyEntity.getPhone(), thirdPartyEntity.getId());
        if (allByPhoneAndIdNot != null && allByPhoneAndIdNot.size() > 0) {
            throw new WafException("", "手机重复", HttpStatus.NOT_ACCEPTABLE);
        }

        if(thirdPartyEntity.getThirdPartyStaffEntities() == null || thirdPartyEntity.getThirdPartyStaffEntities().size() == 0){
            throw new WafException("", "至少添加一名专业技术人员", HttpStatus.NOT_ACCEPTABLE);
        }
        // 保存专业人员信息.
        List<ThirdPartyStaffEntity> thirdPartyStaffEntities = new ArrayList<>();
        for (ThirdPartyStaffEntity thirdPartyStaffEntity : thirdPartyEntity.getThirdPartyStaffEntities()) {
            thirdPartyStaffEntity.setThirdPartyId(thirdPartyEntity.getId());
            thirdPartyStaffEntities.add(thirdPartyStaffEntity);
        }
        // 保存第三方机构信息
        ThirdPartyEntity result = thirdPartyRepository.save(thirdPartyEntity);
        thirdPartyStaffRepository.saveAll(thirdPartyStaffEntities);

        // 填充数据返回
        result.setThirdPartyStaffEntities(thirdPartyStaffEntities);
        return ResultUtil.success(thirdPartyRepository.save(thirdPartyEntity));
    }
}
