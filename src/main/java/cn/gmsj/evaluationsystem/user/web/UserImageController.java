package cn.gmsj.evaluationsystem.user.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.user.service.UserImageService;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by XiaoWen on 2019/8/3
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/user-image")
public class UserImageController {

    @Autowired
    private UserImageService userImageService;

    @PostMapping(
            value = "/save",
            produces = {"application/json;charset=UTF-8"})
    public Object save(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return ResultUtil.error("上传文件不能为空");
        }
        return userImageService.save(file, 1L);
    }
}
