package cn.gmsj.evaluationsystem.projectmanage.web.req;

import cn.gmsj.evaluationsystem.common.web.req.BasePageReq;
import lombok.Data;

/**
 * @author 13562
 */
@Data
public class ProjectInviteListReq extends BasePageReq {
    private Long userId;
}
