package cn.gmsj.evaluationsystem.expertdesign.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.expertdesign.domain.entity.ExpertSuggestEntity;
import cn.gmsj.evaluationsystem.expertdesign.domain.repository.ExpertDesignRepository;
import cn.gmsj.evaluationsystem.expertdesign.web.req.ExperDesignReq;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * 审查业务
 */
@Service
public class ExperDesignService {

    private ExpertDesignRepository expertDesignRepository;

    public JSONObject getDataById(ExperDesignReq ExperDesignReq) {
        if (ExperDesignReq.getId() != null) {
            ExpertSuggestEntity expertSuggestEntity = expertDesignRepository.findAllById(ExperDesignReq.getId());
            if (expertSuggestEntity != null) {
                return ResultUtil.success(expertSuggestEntity);
            } else {
                throw new WafException("", "xxx不存在", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            throw new WafException("", "xxx不存在", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
