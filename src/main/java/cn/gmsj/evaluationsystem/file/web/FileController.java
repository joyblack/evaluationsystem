package cn.gmsj.evaluationsystem.file.web;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.file.domain.entity.FileEntity;
import cn.gmsj.evaluationsystem.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alan
 */
@RestController
@RequestMapping(value = SystemConstant.API_VERSION + "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(
        value = "/updateData",
        method = RequestMethod.POST,
        produces = {"application/json;charset=UTF-8"})
    public Object updateData(MultipartFile file, FileEntity fileEntity, HttpServletRequest request) {
        if (null == file && fileEntity != null && null == fileEntity.getId()) {
            throw new WafException("", "上传文件不能为空", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return fileService.updateData(file, fileEntity, request);
        }
    }

//    @RequestMapping(
//        value = "/getDataById",
//        method = RequestMethod.POST,
//        produces = {"application/json;charset=UTF-8"})
//    public Object getDataById(@RequestBody FileEntity fileEntity) {
//        return fileService.getDataById(fileEntity);
//    }
//
//    @RequestMapping(
//        value = "/deleteDataById",
//        method = RequestMethod.POST,
//        produces = {"application/json;charset=UTF-8"})
//    public Object deleteDataById(@RequestBody FileEntity fileEntity) {
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
//    public Object disposeData(@RequestBody FileEntity fileEntity) {
//        return fileService.disposeData(fileEntity);
//    }
//
//    @RequestMapping(
//        value = "/downloadData",
//        method = RequestMethod.GET)
//    public void downloadData(
//        FileEntity fileEntity, HttpServletRequest request, HttpServletResponse response) {
//        fileService.downloadData(fileEntity, request, response);
//    }
}
