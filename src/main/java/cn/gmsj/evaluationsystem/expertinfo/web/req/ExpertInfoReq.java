package cn.gmsj.evaluationsystem.expertinfo.web.req;

import cn.gmsj.evaluationsystem.common.web.req.BasePageReq;
import cn.gmsj.evaluationsystem.expertinfo.domain.enums.ExpertInfoType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExpertInfoReq {
    private ExpertInfoType expertInfoType;   //专家基本信息状态，提交了就不能看了，能源局审核通过了，在信息维护处可以查询出来。
}
