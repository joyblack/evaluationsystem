package cn.gmsj.evaluationsystem.file.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.file.domain.entity.ExpertInfoFileEntity;
import cn.gmsj.evaluationsystem.file.domain.repository.ExpertInfoFileRepository;
import cn.gmsj.evaluationsystem.utils.FileUtil;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alan
 */
@Service
public class ExpertInfoFileService {

    @Value("${image.format}")
    private String imageFormat;

    @Value("${file.path}")
    private String filePath;

    private String path = "expertInfoFile";

    private final static String FILE_SPLIT = "&&";

    private static String[] filePostfixes={".exe",".bat"};

    @Autowired
    private ExpertInfoFileRepository expertInfoFileRepository;

    public JSONObject uploadFile(MultipartFile file) {
        String name = file.getOriginalFilename();
        String[] names = name.split("\\.");
        if (null == names || names.length == 0) {
            throw new WafException("", "文件错误", HttpStatus.NOT_ACCEPTABLE);
        } else if (Arrays.asList(filePostfixes).contains(names[names.length - 1])) {
            throw new WafException("", "文件类型错误", HttpStatus.NOT_ACCEPTABLE);
        } else {
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

}
