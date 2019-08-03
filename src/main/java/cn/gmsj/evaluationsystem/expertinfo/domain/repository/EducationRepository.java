package cn.gmsj.evaluationsystem.expertinfo.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.EducationEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.PositionalTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends BaseRepository<EducationEntity>, JpaRepository<EducationEntity, Long> {
}
