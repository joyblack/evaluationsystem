package cn.gmsj.evaluationsystem.specialist.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.specialist.domain.entity.PositionalTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionalTypeRepository extends BaseRepository<PositionalTypeEntity>, JpaRepository<PositionalTypeEntity, Long> {
}
