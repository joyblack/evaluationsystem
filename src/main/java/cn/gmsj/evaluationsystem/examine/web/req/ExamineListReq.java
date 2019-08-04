package cn.gmsj.evaluationsystem.examine.web.req;

import cn.gmsj.evaluationsystem.common.web.req.BasePageReq;
import lombok.Data;
import lombok.ToString;

/**
 * Created by XiaoWen on 2019/8/4
 */
@ToString
@Data
public class ExamineListReq extends BasePageReq {

    private String projectName;

}
