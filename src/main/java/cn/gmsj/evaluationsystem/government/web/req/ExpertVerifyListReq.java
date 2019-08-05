package cn.gmsj.evaluationsystem.government.web.req;

import cn.gmsj.evaluationsystem.common.web.req.BasePageReq;
import cn.gmsj.evaluationsystem.specialist.domain.enums.ExpertInfoType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExpertVerifyListReq extends BasePageReq {
    private ExpertInfoType expertInfoType;

    private String name;

    private String idCard;
}
