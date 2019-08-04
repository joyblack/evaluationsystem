package cn.gmsj.evaluationsystem.activityrecord.web;

import cn.gmsj.evaluationsystem.activityrecord.service.ActivityRecordService;
import cn.gmsj.evaluationsystem.activityrecord.web.req.ActivityRecordListReq;
import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by XiaoWen on 2019/8/3
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/activity-record")
public class ActivityRecordController {

    @Autowired
    private ActivityRecordService activityRecordService;

    @RequestMapping(
            value = "/getAllByUser",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public Object getAllByUser(@RequestBody ActivityRecordListReq activityRecordListReq, HttpServletRequest request) {
        return activityRecordService.getAllByUser(activityRecordListReq, request);
    }
}
