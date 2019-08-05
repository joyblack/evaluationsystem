package cn.gmsj.evaluationsystem.file.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.ExpertInfoEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.repository.ExpertInfoRepository;
import cn.gmsj.evaluationsystem.file.domain.entity.ExpertInfoFileEntity;
import cn.gmsj.evaluationsystem.file.domain.entity.ExpertInfoImageEntity;
import cn.gmsj.evaluationsystem.file.domain.repository.ExpertInfoFileRepository;
import cn.gmsj.evaluationsystem.file.domain.repository.ExpertInfoImageRepository;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserImageEntity;
import cn.gmsj.evaluationsystem.utils.FileUtil;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Alan
 */
@Service
public class ExpertInfoFileService {

    @Value("${image.format}")
    private String imageFormat;

    @Value("${file.path}")
    private String filePath;

    private String path = "expertInfoImage";

    private final static String FILE_SPLIT = "&&";

    private static String[] filePostfixes={".exe",".bat"};

    @Autowired
    private ExpertInfoFileRepository expertInfoFileRepository;

    @Autowired
    private ExpertInfoImageRepository expertInfoImageRepository;

    @Autowired
    private ExpertInfoRepository expertInfoRepository;

    public JSONObject uploadFile(MultipartFile file,UserEntity userEntity) {
        ExpertInfoEntity expertInfoEntity=expertInfoRepository.findAllByIdCard(userEntity.getIdNumber());
        if(expertInfoEntity==null){
            throw new WafException("", "专家基本信息不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        String name = file.getOriginalFilename();
        String[] names = name.split("\\.");
        if (null == names || names.length == 0) {
            throw new WafException("", "文件错误", HttpStatus.NOT_ACCEPTABLE);
        } else if (Arrays.asList(filePostfixes).contains(names[names.length - 1])) {
            throw new WafException("", "文件类型错误", HttpStatus.NOT_ACCEPTABLE);
        }else {
            int num=0;
            List<ExpertInfoFileEntity> expertInfoFileEntityOld = expertInfoFileRepository.findAllByNameOrderByCreateTimeDesc(name);
            if(expertInfoFileEntityOld!=null&&expertInfoFileEntityOld.size()>0){
                String[] str=expertInfoFileEntityOld.get(0).getUuidFileName().split("\\.");
                String[] prefix=str[0].split("-");
                int index=Integer.parseInt(prefix[2]);
                index=index+1;
                num=index;
            }else {
                num=1;
            }
            StringBuffer newFileName = new StringBuffer();
            newFileName.append(names[0]).append("-").append(num).append(".").append(names[names.length - 1]);
            String[] filePaths = filePath.split(FILE_SPLIT);
            StringBuffer stringBuffer = new StringBuffer();
            for (String s : filePaths) {
                stringBuffer.append(s + File.separator);
            }
            String fileFinalPath = stringBuffer.toString() + path;
            String uuid = IdUtil.simpleUUID();
            ExpertInfoFileEntity expertInfoFileEntity=new ExpertInfoFileEntity();
            expertInfoFileEntity.setName(name);
            expertInfoFileEntity.setPath(fileFinalPath + File.separator + newFileName);
            expertInfoFileEntity.setUuid(uuid);
            expertInfoFileEntity.setUuidFileName(uuid + "-" + newFileName);
            expertInfoFileEntity.setExpertInfoEntity(expertInfoEntity);
            try {
                FileUtil.save(file.getBytes(), fileFinalPath,newFileName.toString());
            } catch (IOException e) {
                throw new WafException("", "文件写入错误", HttpStatus.NOT_ACCEPTABLE);
            }
            return ResultUtil.success(expertInfoFileRepository.save(expertInfoFileEntity));
        }
    }

    public JSONObject getUploadFile(String uuid) {
        ExpertInfoFileEntity expertInfoFileEntity=expertInfoFileRepository.findAllByUuid(uuid);
        if(expertInfoFileEntity==null){
            throw new WafException("", "文件不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(expertInfoFileEntity);
    }

    public JSONObject uploadImage(MultipartFile file,UserEntity userEntity) {
        ExpertInfoImageEntity expertInfoImageEntity=new ExpertInfoImageEntity();
        // 检查文件
        String name = file.getOriginalFilename();
        expertInfoImageEntity.setName(name);
        String[] names = name.split("\\.");
        if (null == names || names.length == 0) {
            return ResultUtil.error("文件错误");
        }
        if (!FileUtil.fileNamePostfixCheck(imageFormat, names[names.length - 1])) {
            return ResultUtil.error("文件类型错误");
        }
        // 保存文件名
        String uuid = UUID.randomUUID().toString().replace("-", "");
        expertInfoImageEntity.setUuid(uuid);
        String fileName = uuid + "." + names[1];
        expertInfoImageEntity.setName(fileName);
        // 保存路径
        String[] filePaths = filePath.split(FILE_SPLIT);
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : filePaths) {
            stringBuffer.append(s + File.separator);
        }
        String fileFinalPath = stringBuffer.toString() + path;
        expertInfoImageEntity.setPath(fileFinalPath + File.separator + fileName);
        expertInfoImageEntity.setUuidName(uuid + "-" + name + names[names.length - 1]);
        expertInfoImageEntity.setUserEntity(userEntity);
        // 保存上传文件
        try {
            FileUtil.save(file.getBytes(), fileFinalPath, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error("文件上传失败");
        }
        return ResultUtil.success(expertInfoImageRepository.save(expertInfoImageEntity));
    }

    /**
     * 获取图片
     * @param uuid
     * @return
     */
    public JSONObject getUploadImage(String uuid) {
        ExpertInfoImageEntity expertInfoImageEntity=expertInfoImageRepository.findAllByUuid(uuid);
        if(expertInfoImageEntity==null){
            throw new WafException("", "文件不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResultUtil.success(expertInfoImageEntity);
    }

}
