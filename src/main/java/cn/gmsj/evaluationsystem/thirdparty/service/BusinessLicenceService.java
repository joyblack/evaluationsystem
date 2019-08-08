package cn.gmsj.evaluationsystem.thirdparty.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.specialist.domain.entity.ExpertInfoFileEntity;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.BusinessLicenceEntity;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.ThirdPartyEntity;
import cn.gmsj.evaluationsystem.thirdparty.domain.repository.BusinessLicenceRepository;
import cn.gmsj.evaluationsystem.thirdparty.domain.repository.ThirdPartyRepository;
import cn.gmsj.evaluationsystem.thirdparty.web.res.ThirdPartyRes;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserImageEntity;
import cn.gmsj.evaluationsystem.user.domain.reposiory.UserRepository;
import cn.gmsj.evaluationsystem.utils.FileUtil;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.UpdateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BusinessLicenceService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @Value("${image.format}")
    private String imageFormat;

    @Value("${file.path}")
    private String filePath;

    private String path = "business_licence";

    private final static String FILE_SPLIT = "&&";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessLicenceRepository businessLicenceRepository;



    public JSONObject upload(MultipartFile file, Long userId) {
        // 检查文件
        String name = file.getOriginalFilename();
        String[] names = name.split("\\.");
        if (null == names || names.length == 0) {
            return ResultUtil.error("文件错误");
        }
        if (!FileUtil.fileNamePostfixCheck(imageFormat, names[names.length - 1])) {
            return ResultUtil.error("文件类型错误");
        }

        ThirdPartyEntity thirdPartyEntity = thirdPartyRepository.findAllByUserId(userId);
        if(thirdPartyEntity == null){
            return ResultUtil.error("第三方机构信息不存在");
        }

        BusinessLicenceEntity businessLicenceEntity = new BusinessLicenceEntity();
        // 所属第三方机构
        businessLicenceEntity.setThirdPartyEntity(thirdPartyEntity);
        // uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        businessLicenceEntity.setUuid(uuid);
        // 保存文件名
        String fileName = uuid + "." + names[1];
        businessLicenceEntity.setName(fileName);
        // 保存路径：公共存储目录+business_licence
        String[] filePaths = filePath.split(FILE_SPLIT);
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : filePaths) {
            stringBuffer.append(s + File.separator);
        }
        String fileFinalPath = stringBuffer.toString() + path;
        businessLicenceEntity.setPath(fileFinalPath + File.separator + fileName);
        // 保存上传文件
        try {
            FileUtil.save(file.getBytes(), fileFinalPath, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error("文件上传失败");
        }
        return ResultUtil.success(businessLicenceRepository.save(businessLicenceEntity));
    }

    public BusinessLicenceEntity getFile(String uuid) {
        BusinessLicenceEntity businessLicenceEntity = businessLicenceRepository.findAllByUuid(uuid);
        System.out.println("The searched licence info is: " + businessLicenceEntity);
        if(businessLicenceEntity == null){
            throw new WafException("", "文件不存在", HttpStatus.NOT_ACCEPTABLE);
        }
        return businessLicenceEntity;
    }
}
