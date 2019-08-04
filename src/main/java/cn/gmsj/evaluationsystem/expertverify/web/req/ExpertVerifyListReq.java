package cn.gmsj.evaluationsystem.expertverify.web.req;

import cn.gmsj.evaluationsystem.common.web.req.BasePageReq;
import cn.gmsj.evaluationsystem.expertinfo.domain.enums.ExpertInfoType;
import cn.gmsj.evaluationsystem.expertinfo.domain.enums.SexType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExpertVerifyListReq extends BasePageReq {
    private ExpertInfoType expertInfoType;

    private String name;

    private String idCard;
}
