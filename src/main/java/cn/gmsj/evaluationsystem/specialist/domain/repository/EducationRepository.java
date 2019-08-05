package cn.gmsj.evaluationsystem.specialist.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.specialist.domain.entity.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends BaseRepository<EducationEntity>, JpaRepository<EducationEntity, Long> {
}
