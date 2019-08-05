package cn.gmsj.evaluationsystem.specialist.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.specialist.domain.entity.DeclareMajorTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclareMajorTypeRepository extends BaseRepository<DeclareMajorTypeEntity>, JpaRepository<DeclareMajorTypeEntity, Long> {
}
