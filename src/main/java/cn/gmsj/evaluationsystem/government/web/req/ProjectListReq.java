package cn.gmsj.evaluationsystem.government.web.req;

import cn.gmsj.evaluationsystem.common.web.req.BasePageReq;
import lombok.Data;

@Data
public class ProjectListReq extends BasePageReq {


    /**
     * 项目名
     */
    private  String projectName;

}
