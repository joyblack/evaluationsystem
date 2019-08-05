package cn.gmsj.evaluationsystem.file.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.ExpertInfoEntity;
import cn.gmsj.evaluationsystem.file.domain.entity.ExpertInfoFileEntity;
import cn.gmsj.evaluationsystem.file.service.ExpertInfoFileService;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.utils.FileUtil;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.TokenUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public Object uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest req) {
        UserEntity userEntity= TokenUtil.getUser(req);
        if(userEntity==null){
            throw new WafException("","用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        if (null == file) {
            throw new WafException("", "上传文件不能为空", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return expertInfoFileService.uploadFile(file,userEntity);
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
    public void getUploadFile(@PathVariable("uuid")String uuid, HttpServletRequest request, HttpServletResponse response) {
        JSONObject res=expertInfoFileService.getUploadFile(uuid);
        ExpertInfoFileEntity expertInfoFileEntity=(ExpertInfoFileEntity)res.get("data");
        FileUtil.downloadDataFile(expertInfoFileEntity,request,response);
        //FileUtil.downloadFile(expertInfoFileEntity.getName(),expertInfoFileEntity.getPath(),request,response);
    }

    /**
     * 获取上传图片
     * @param uuid
     * @return
     */
    @RequestMapping(
            value = "/getUploadImage/{uuid}",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public Object getUploadImage(@PathVariable("uuid")String uuid) {
        return expertInfoFileService.getUploadImage(uuid);
    }

    /**
     * 上传图片
     */
    @PostMapping(
            value = "/uploadImage",
            produces = {"application/json;charset=UTF-8"})
    public Object uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
        UserEntity userEntity= TokenUtil.getUser(req);
        if(userEntity==null){
            throw new WafException("","用户信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        if (file.isEmpty()) {
            return ResultUtil.error("上传文件不能为空");
        }
        return expertInfoFileService.uploadImage(file,userEntity);
    }

}
