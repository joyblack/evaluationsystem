package cn.gmsj.evaluationsystem.file.service;

import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.file.domain.entity.ExpertInfoFileEntity;
import cn.gmsj.evaluationsystem.file.domain.repository.ExpertInfoFileRepository;
import cn.gmsj.evaluationsystem.utils.ResultUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FileService {

//    @Value("${file.postfix}")
////    private String filePostfix;
////
////    @Value("${file.base.path}")
////    private String basePath;

    private static String[] filePostfixes={".exe",".bat"};

    @Autowired
    private ExpertInfoFileRepository expertInfoFileRepository;

    public JSONObject updateData(MultipartFile file) {
        String basePath="H:\file";
        String name = file.getOriginalFilename();
        String[] names = name.split("\\.");
        if (null == names || names.length == 0) {
            throw new WafException("", "文件错误", HttpStatus.NOT_ACCEPTABLE);
        } else if (Arrays.asList(filePostfixes).contains(names[names.length - 1])) {
            throw new WafException("", "文件类型错误", HttpStatus.NOT_ACCEPTABLE);
        } else {
            ExpertInfoFileEntity expertInfoFileEntityOld = expertInfoFileRepository.findAllByName(name);
//            StringBuffer names = new StringBuffer();
//            if(expertInfoFileEntityOld!=null){
//                names.append(name).append()
//            }
            String uuid = IdUtil.simpleUUID();
            ExpertInfoFileEntity expertInfoFileEntity=new ExpertInfoFileEntity();
            expertInfoFileEntity.setName(name);
            expertInfoFileEntity.setPath(basePath);
            expertInfoFileEntity.setUuid(uuid);
            expertInfoFileEntity.setUuidFileName(uuid + "." + names[names.length - 1]);
            try {
                FileUtil.writeBytes(file.getBytes(),
                        new File(basePath));
            } catch (IOException e) {
                throw new WafException("", "文件写入错误", HttpStatus.NOT_ACCEPTABLE);
            }
            return ResultUtil.success(expertInfoFileRepository.save(expertInfoFileEntity));
        }
    }

//    public JSONObject getDataById(ExpertInfoFileEntity fileEntity) {
//        if (fileEntity != null && fileEntity.getId() != null) {
//            return ResultUtil.success(fileRepository.findById(fileEntity.getId()));
//        } else {
//            throw new WafException("", "文件信息不存在", HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//    public JSONObject deleteDataById(ExpertInfoFileEntity fileEntity) {
//        if (fileEntity != null && fileEntity.getId() != null) {
//            ExpertInfoFileEntity fileEntity1 = fileRepository.findAllById(fileEntity.getId());
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
//        Page<ExpertInfoFileEntity> fileEntities = fileRepository.findAll(
//            new Specification<ExpertInfoFileEntity>() {
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
//    public JSONObject disposeData(ExpertInfoFileEntity fileEntity) {
//        if (fileEntity != null && fileEntity.getId() != null) {
//            ExpertInfoFileEntity fileEntity1 = fileRepository.findAllById(fileEntity.getId());
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
//    public void downloadData(ExpertInfoFileEntity fileEntity, HttpServletRequest request, HttpServletResponse response) {
//        if (fileEntity != null && fileEntity.getId() != null) {
//            ExpertInfoFileEntity fileEntity1 = fileRepository.findAllById(fileEntity.getId());
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
