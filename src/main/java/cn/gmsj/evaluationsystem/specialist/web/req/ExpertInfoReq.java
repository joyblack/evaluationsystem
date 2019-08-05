package cn.gmsj.evaluationsystem.specialist.web.req;

import cn.gmsj.evaluationsystem.common.web.req.BasePageReq;
import cn.gmsj.evaluationsystem.specialist.domain.enums.ExpertInfoType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExpertInfoReq {
    private ExpertInfoType expertInfoType;   //专家基本信息状态，提交了就不能看了，能源局审核通过了，在信息维护处可以查询出来。

    /**
     * Created by XiaoWen on 2019/8/4
     */
    @ToString
    @Data
    public static class ExamineListReq extends BasePageReq {

        private String projectName;

    }
}
