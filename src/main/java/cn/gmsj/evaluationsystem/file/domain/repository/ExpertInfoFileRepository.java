package cn.gmsj.evaluationsystem.file.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.file.domain.entity.ExpertInfoFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alan
 */
public interface ExpertInfoFileRepository extends BaseRepository<ExpertInfoFileEntity>, JpaRepository<ExpertInfoFileEntity, Long> {
    List<ExpertInfoFileEntity> findAllByNameAndIdNot(String name, Long id);

    ExpertInfoFileEntity findAllByName(String name);


}
