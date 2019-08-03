package cn.gmsj.evaluationsystem.expertinfo.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.EducationEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.EngagedMajorEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.StudyMajorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EngagedMajorRepository extends BaseRepository<EngagedMajorEntity>, JpaRepository<EngagedMajorEntity, Long> {
    List<EngagedMajorEntity> findAllByEngagedMajorNameIn(List<String> engagedMajorsStr);

    List<EngagedMajorEntity> findAllByIdIn(List<Long> ids);
}
