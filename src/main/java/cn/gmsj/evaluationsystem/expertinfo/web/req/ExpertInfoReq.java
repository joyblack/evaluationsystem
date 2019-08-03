package cn.gmsj.evaluationsystem.expertinfo.web.req;

import cn.gmsj.evaluationsystem.common.web.req.BasePageReq;
import cn.gmsj.evaluationsystem.expertinfo.domain.enums.ExpertInfoType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExpertInfoReq {
    private ExpertInfoType expertInfoType;
    private String idCard;
}
