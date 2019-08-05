package cn.gmsj.evaluationsystem.specialist.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.specialist.domain.entity.DeclareMajorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclareMajorRepository extends BaseRepository<DeclareMajorEntity>, JpaRepository<DeclareMajorEntity, Long> {
    List<DeclareMajorEntity> findAllByDeclareMajorNameIn(List<String> declareMajorsStr);
    List<DeclareMajorEntity> findAllByIdIn(List<Long> ids);
}
