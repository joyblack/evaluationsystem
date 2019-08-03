package cn.gmsj.evaluationsystem.expertinfo.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.DeclareMajorEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.EducationEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.EngagedMajorEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.StudyMajorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclareMajorRepository extends BaseRepository<DeclareMajorEntity>, JpaRepository<DeclareMajorEntity, Long> {
    List<DeclareMajorEntity> findAllByDeclareMajorNameIn(List<String> declareMajorsStr);
    List<DeclareMajorEntity> findAllByIdIn(List<Long> ids);
}
