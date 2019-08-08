package cn.gmsj.evaluationsystem.thirdparty.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.specialist.domain.entity.*;
import cn.gmsj.evaluationsystem.specialist.web.res.DeclareMajorArrayRes;
import cn.gmsj.evaluationsystem.specialist.web.res.ExpertInfoRes;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.ThirdPartyEntity;
import cn.gmsj.evaluationsystem.thirdparty.domain.repository.ThirdPartyRepository;
import cn.gmsj.evaluationsystem.thirdparty.web.res.ThirdPartyRes;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.utils.IdNumberUtil;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.UpdateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class ThirdPartyService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

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
        if(null == thirdPartyEntity.getId()){
            thirdPartyEntity.setId(0L);
            thirdPartyEntity.setUser(userEntity);
        }else{
            // copy from database
            UpdateUtil.copyProperties(thirdPartyRepository.findAllById(thirdPartyEntity.getId()),thirdPartyEntity);
            // set update time
            thirdPartyEntity.setUpdateTime(new Date());
        }
        System.out.println(thirdPartyEntity);
        List<ThirdPartyEntity> allByPhoneAndIdNot = thirdPartyRepository.findAllByPhoneAndIdNot(thirdPartyEntity.getPhone(), thirdPartyEntity.getId());
        if (allByPhoneAndIdNot != null && allByPhoneAndIdNot.size() > 0) {
            throw new WafException("", "手机重复", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResultUtil.success(thirdPartyRepository.save(thirdPartyEntity));
    }
}
