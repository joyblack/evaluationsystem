package cn.gmsj.evaluationsystem.file.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.file.domain.entity.FileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alan
 */
public interface FileRepository extends BaseRepository<FileEntity>, JpaRepository<FileEntity, Long> {
    List<FileEntity> findAllByNameAndIdNot(String name,Long id);


}
