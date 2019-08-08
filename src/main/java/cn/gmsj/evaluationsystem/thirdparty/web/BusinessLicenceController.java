package cn.gmsj.evaluationsystem.thirdparty.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.specialist.domain.entity.ExpertInfoFileEntity;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.BusinessLicenceEntity;
import cn.gmsj.evaluationsystem.thirdparty.service.BusinessLicenceService;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.service.UserImageService;
import cn.gmsj.evaluationsystem.utils.FileUtil;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.TokenUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by XiaoWen on 2019/8/3
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/business-licence")
public class BusinessLicenceController {

    @Autowired
    private BusinessLicenceService businessLicenceService;

    /**
     * 保存营业执照信息
     */
    @PostMapping(
            value = "/upload",
            produces = {"application/json;charset=UTF-8"})
    public Object upload(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
        UserEntity userEntity= TokenUtil.getUser(req);
        System.out.println("userId:" + userEntity.getId());
        if (file.isEmpty()) {
            return ResultUtil.error("上传文件不能为空");
        }
        return businessLicenceService.upload(file, Long.valueOf(userEntity.getId()));
    }


    /**
     * 获取营业执照
     */
    @RequestMapping(
            value = "/downloadFile/{uuid}",
            method = RequestMethod.GET)
    public void downloadFile(@PathVariable("uuid")String uuid, HttpServletRequest request, HttpServletResponse response) {
        BusinessLicenceEntity file = businessLicenceService.getFile(uuid);
        // JOY_NEXT: This name maybe write be the third party? Now just set with file name.
        FileUtil.downloadFile(file.getName(),file.getPath(), request,response);
    }


}
