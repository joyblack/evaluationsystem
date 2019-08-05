package cn.gmsj.evaluationsystem.specialist.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.specialist.domain.entity.PositionalTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionalTitleRepository extends BaseRepository<PositionalTitleEntity>, JpaRepository<PositionalTitleEntity, Long> {
}
