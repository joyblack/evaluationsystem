package cn.gmsj.evaluationsystem.activityrecord.web.req;

import cn.gmsj.evaluationsystem.common.web.req.BasePageReq;
import cn.gmsj.evaluationsystem.enums.UserDataType;

/**
 * Created by XiaoWen on 2019/8/3
 */
public class ActivityRecordListReq extends BasePageReq {

    private UserDataType userDataType;

    public UserDataType getUserDataType() {
        return userDataType;
    }

    public void setUserDataType(UserDataType userDataType) {
        this.userDataType = userDataType;
    }
}
