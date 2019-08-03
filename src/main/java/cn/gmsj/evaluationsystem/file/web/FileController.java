package cn.gmsj.evaluationsystem.file.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alan
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     */
    @RequestMapping(
        value = "/updateData",
        method = RequestMethod.POST)
    public Object updateData(MultipartFile file) {
        System.out.println(">>>>>>>>");
        if (null == file) {
            throw new WafException("", "上传文件不能为空", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return fileService.updateData(file);
        }
    }

//    @RequestMapping(
//        value = "/getDataById",
//        method = RequestMethod.POST,
//        produces = {"application/json;charset=UTF-8"})
//    public Object getDataById(@RequestBody ExpertInfoFileEntity fileEntity) {
//        return fileService.getDataById(fileEntity);
//    }
//
//    @RequestMapping(
//        value = "/deleteDataById",
//        method = RequestMethod.POST,
//        produces = {"application/json;charset=UTF-8"})
//    public Object deleteDataById(@RequestBody ExpertInfoFileEntity fileEntity) {
//        return fileService.deleteDataById(fileEntity);
//    }
//
//    @RequestMapping(
//        value = "/getData",
//        method = RequestMethod.POST,
//        produces = {"application/json;charset=UTF-8"})
//    public Object getData(@RequestBody FileListReq fileListReq, HttpServletRequest request) {
//        if (null == fileListReq.getAffiliationMine()) {
//            fileListReq.setAffiliationMine(RequestUtil.getMine(request));
//        }
//        return fileService.getData(fileListReq);
//    }
//
//    @RequestMapping(
//        value = "/disposeData",
//        method = RequestMethod.POST,
//        produces = {"application/json;charset=UTF-8"})
//    public Object disposeData(@RequestBody ExpertInfoFileEntity fileEntity) {
//        return fileService.disposeData(fileEntity);
//    }
//
//    @RequestMapping(
//        value = "/downloadData",
//        method = RequestMethod.GET)
//    public void downloadData(
//        ExpertInfoFileEntity fileEntity, HttpServletRequest request, HttpServletResponse response) {
//        fileService.downloadData(fileEntity, request, response);
//    }
}
