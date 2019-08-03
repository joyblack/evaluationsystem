package cn.gmsj.evaluationsystem.user.service;

import cn.gmsj.evaluationsystem.user.domain.entity.UserImageEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.domain.model.ResultFile;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserImageRepository;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserRepository;
import cn.gmsj.evaluationsystem.utils.FileUtill;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by XiaoWen on 2019/8/3
 */
@Service
public class UserImageService {

    @Autowired
    private UserRepository userRepository;

    @Value("${image.format}")
    private String imageFormat;

    @Value("${file.path}")
    private String filePath;

    private String path = "businessLicenceImage";

    private final static String FILE_SPLIT = "&&";

    @Autowired
    private UserImageRepository userImageRepository;

    public JSONObject save(MultipartFile file, Long userId) {
        UserEntity userEntity = userRepository.findAllById(userId);
        if(null == userEntity){
            return ResultUtil.error("第三方机构信息不存在");
        }
        UserImageEntity userImageEntity = new UserImageEntity();
        // 检查文件
        String name = file.getOriginalFilename();
        userImageEntity.setCorrespondName(name);
        String[] names = name.split("\\.");
        if (null == names || names.length == 0) {
            return ResultUtil.error("文件错误");
        }
        if (FileUtill.fileNamePostfixCheck(imageFormat, names[names.length - 1])) {
            return ResultUtil.error("文件类型错误");
        }
        // 保存文件名
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userImageEntity.setUuid(uuid);
        String fileName = uuid + "." + names[1];
        userImageEntity.setName(fileName);
        // 保存路径
        String[] filePaths = filePath.split(FILE_SPLIT);
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : filePaths) {
            stringBuffer.append(s + File.separator);
        }
        String fileFinalPath = stringBuffer.toString() + path;
        userImageEntity.setPath(fileFinalPath + File.separator + fileName);
        // 保存上传文件
        try {
            FileUtill.save(file.getBytes(), fileFinalPath, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error("文件上传失败");
        }
        UserImageEntity bsinessLicenceImageEntity = userImageRepository.save(userImageEntity);
        ResultFile resultFile = new ResultFile();
        resultFile.setId(bsinessLicenceImageEntity.getId());
        resultFile.setName(bsinessLicenceImageEntity.getCorrespondName());
        // resultFile.setUrl("/system/userImage/getById");
        resultFile.setUuid(uuid);
        // return ResultUtil.success(resultFile);
        return ResultUtil.success();
    }
}
