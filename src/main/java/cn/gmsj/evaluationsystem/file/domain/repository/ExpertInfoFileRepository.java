package cn.gmsj.evaluationsystem.file.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.file.domain.entity.ExpertInfoFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Alan
 */
public interface ExpertInfoFileRepository extends BaseRepository<ExpertInfoFileEntity>, JpaRepository<ExpertInfoFileEntity, Long> {
    List<ExpertInfoFileEntity> findAllByNameAndIdNot(String name, Long id);


    List<ExpertInfoFileEntity> findAllByNameOrderByCreateTimeDesc(String name);

    ExpertInfoFileEntity findAllByUuid(String uuid);

    List<ExpertInfoFileEntity> findAllByOrderByCreateTimeDesc();


}
