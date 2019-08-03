package cn.gmsj.evaluationsystem.file.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.file.domain.entity.FileEntity;
import cn.gmsj.evaluationsystem.file.domain.repository.FileRepository;
import cn.gmsj.evaluationsystem.file.web.req.FileListReq;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.gmsj.evaluationsystem.utils.StringUtil;
import cn.gmsj.evaluationsystem.utils.UpdateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Alan
 */
@Service
public class FileService {

//    @Value("${file.postfix}")
////    private String filePostfix;
////
////    @Value("${file.base.path}")
////    private String basePath;

    private static String[] filePostfix={".exe",".bat"};

    @Autowired
    private FileRepository fileRepository;

    public JSONObject updateData(MultipartFile file, FileEntity fileEntity, HttpServletRequest request) {
//        String basePath="H:\file";
//        if (fileEntity.getId() != null && file == null) {
//            UpdateUtil.copyProperties(fileRepository.findAllById(fileEntity.getId()), fileEntity);
//            fileEntity.setUpdateTime(new Date());
//            FileEntity fileEntity1 = fileRepository.save(fileEntity);
//            return ResultUtil.success(fileEntity1);
//        } else {
//            String[] filePostfixes = filePostfix.split();
//            String name = file.getOriginalFilename();
//            String[] names = name.split("\\.");
//            if (null == names || names.length == 0) {
//                throw new WafException("", "文件错误", HttpStatus.NOT_ACCEPTABLE);
//            } else if (Arrays.asList(filePostfixes).contains(names[names.length - 1])) {
//                throw new WafException("", "文件类型错误", HttpStatus.NOT_ACCEPTABLE);
//            } else {
//                List<FileEntity> fileEntities = fileRepository.findAllByNameAndIdNot(name,fileEntity.getId());
//                if (fileEntities != null && fileEntities.size() > 0) {
//                    throw new WafException("", "文件名称重复", HttpStatus.NOT_ACCEPTABLE);
//                }
//                String uuid = IdUtil.simpleUUID();
//                fileEntity.setName(name);
//                fileEntity.setUuid(uuid);
//                fileEntity.setUuidFileName(uuid + "." + names[names.length - 1]);
//                String[] basePaths = basePath.split(ConstantsEnum.SEGMENTATION.getName());
//                StringBuffer filePaths = new StringBuffer();
//                for (String s : basePaths) {
//                    filePaths.append(s + File.separator);
//                }
//                filePaths.append(fileEntity.getUuidFileName());
//                fileEntity.setPath(filePaths.toString());
//                try {
//                    FileUtil.writeBytes(file.getBytes(),
//                        new File(filePaths.toString()));
//                } catch (IOException e) {
//                    throw new WafException("", "文件写入错误", HttpStatus.NOT_ACCEPTABLE);
//                }
//                FileEntity fileEntity1 = fileRepository.save(fileEntity);
//                return ResultUtil.success(fileEntity1);
//            }
//        }
        return ResultUtil.success();
    }

//    public JSONObject getDataById(FileEntity fileEntity) {
//        if (fileEntity != null && fileEntity.getId() != null) {
//            return ResultUtil.success(fileRepository.findById(fileEntity.getId()));
//        } else {
//            throw new WafException("", "文件信息不存在", HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//    public JSONObject deleteDataById(FileEntity fileEntity) {
//        if (fileEntity != null && fileEntity.getId() != null) {
//            FileEntity fileEntity1 = fileRepository.findAllById(fileEntity.getId());
//            if (fileEntity1 != null) {
//                File file = new File(fileEntity1.getPath());
//                if (file.exists() && file.isFile()) {
//                    if (file.delete()) {
//                        fileRepository.delete(fileEntity1);
//                        return ResultUtil.success();
//                    } else {
//                        throw new WafException("", "文件删除失败", HttpStatus.NOT_ACCEPTABLE);
//                    }
//                } else {
//                    throw new WafException("", "文件信息不存在", HttpStatus.NOT_ACCEPTABLE);
//                }
//            } else {
//                throw new WafException("", "文件信息不存在", HttpStatus.NOT_ACCEPTABLE);
//            }
//        } else {
//            throw new WafException("", "文件信息不存在", HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//    public JSONObject getData(FileListReq fileListReq) {
//        Sort sort = null;
//        if (StringUtil.isNotEmpty(fileListReq.getSortKey())) {
//            if (2 == fileListReq.getSortType()) {
//                sort = new Sort(Sort.Direction.DESC, fileListReq.getSortKey());
//            } else {
//                sort = new Sort(Sort.Direction.ASC, fileListReq.getSortKey());
//            }
//        }
//        Pageable pageable = null;
//        if (sort != null) {
//            pageable = PageRequest.of(fileListReq.getPage() - 1, fileListReq.getSize(), sort);
//        } else {
//            pageable = PageRequest.of(fileListReq.getPage() - 1, fileListReq.getSize());
//        }
//        Page<FileEntity> fileEntities = fileRepository.findAll(
//            new Specification<FileEntity>() {
//                @Override
//                public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
//                    List<Predicate> predicates = new ArrayList<Predicate>();
//                    if (StringUtil.isNotEmpty(fileListReq.getName())) {
//                        predicates.add(cb.like(root.<String>get("name"), "%" + fileListReq.getName() + "%"));
//                    }
//                    if (fileListReq.getFileDisposeStatus() != null) {
//                        predicates.add(cb.equal(root.<Enum>get("fileDisposeStatus"),
//                            fileListReq.getFileDisposeStatus()));
//                    }
//                    if (fileListReq.getAffiliationMine() != null) {
//                        predicates.add(cb.equal(root.<MineEntity>get("affiliationMine"),
//                            fileListReq.getAffiliationMine()));
//                    }
//                    query.where(predicates.toArray(new Predicate[predicates.size()]));
//                    query.orderBy(cb.desc(root.<Date>get("createTime").as(Date.class)));
//                    return query.getRestriction();
//                }
//            },
//            pageable);
//        return ResultUtil.pageSuccess(fileEntities);
//    }
//
//    public JSONObject disposeData(FileEntity fileEntity) {
//        if (fileEntity != null && fileEntity.getId() != null) {
//            FileEntity fileEntity1 = fileRepository.findAllById(fileEntity.getId());
//            if (fileEntity1 != null) {
//                fileEntity1.setFileDisposeStatus(FileDisposeStatusEnum.PROCESSED);
//                fileRepository.save(fileEntity1);
//                return ResultUtil.success();
//            } else {
//                throw new WafException("", "文件信息不存在", HttpStatus.NOT_ACCEPTABLE);
//            }
//        } else {
//            throw new WafException("", "文件信息不存在", HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//    public void downloadData(FileEntity fileEntity, HttpServletRequest request, HttpServletResponse response) {
//        if (fileEntity != null && fileEntity.getId() != null) {
//            FileEntity fileEntity1 = fileRepository.findAllById(fileEntity.getId());
//            if (fileEntity1 != null) {
//                File file = new File(fileEntity1.getPath());
//                if (file.exists()) {
//                    // 设置强制下载不打开
//                    response.setContentType("application/force-download");
//                    // 设置文件名
//                    String codedFileName = null;
//                    String agent = request.getHeader("USER-AGENT").toLowerCase();
//                    try {
//                        codedFileName = URLEncoder.encode(fileEntity1.getName(), "UTF-8");
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                    if (agent.contains("firefox")) {
//                        response.setCharacterEncoding("utf-8");
//                        try {
//                            response.setHeader(
//                                "content-disposition",
//                                "attachment;filename=" + new String(fileEntity1.getName().getBytes(), "ISO8859-1"));
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
//                    }
//                    byte[] buffer = new byte[1024];
//                    FileInputStream fis = null;
//                    BufferedInputStream bis = null;
//                    try {
//                        fis = new FileInputStream(file);
//                        bis = new BufferedInputStream(fis);
//                        OutputStream os = response.getOutputStream();
//                        int i = bis.read(buffer);
//                        while (i != -1) {
//                            os.write(buffer, 0, i);
//                            i = bis.read(buffer);
//                        }
//                    } catch (Exception e) {
//                        throw new WafException("", "文件读写错误", HttpStatus.NOT_ACCEPTABLE);
//                    } finally {
//                        if (bis != null) {
//                            try {
//                                bis.close();
//                            } catch (IOException e) {
//                                throw new WafException("", "文件读写错误", HttpStatus.NOT_ACCEPTABLE);
//                            }
//                        }
//                        if (fis != null) {
//                            try {
//                                fis.close();
//                            } catch (IOException e) {
//                                throw new WafException("", "文件读写错误", HttpStatus.NOT_ACCEPTABLE);
//                            }
//                        }
//                    }
//                }
//            } else {
//                throw new WafException("", "文件信息不存在", HttpStatus.NOT_ACCEPTABLE);
//            }
//        } else {
//            throw new WafException("", "文件信息不存在", HttpStatus.NOT_ACCEPTABLE);
//        }
//    }

}
