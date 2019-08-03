package cn.gmsj.evaluationsystem.file.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.file.service.ExpertInfoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Alan
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/file")
public class ExpertInfoFileController {

    @Autowired
    private ExpertInfoFileService expertInfoFileService;

    /**
     * 上传文件
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public Object uploadFile(@RequestParam("file") MultipartFile file) {
        if (null == file) {
            throw new WafException("", "上传文件不能为空", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return expertInfoFileService.uploadFile(file);
        }
    }

    /**
     * 获取上传文件
     * @param uuid
     * @return
     */
    @RequestMapping(
        value = "/getUploadFile/{uuid}",
        method = RequestMethod.GET,
        produces = {"application/json;charset=UTF-8"})
    public Object getUploadFile(@PathVariable("uuid")String uuid) {
        return expertInfoFileService.getUploadFile(uuid);
    }

}
