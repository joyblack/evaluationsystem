package cn.gmsj.evaluationsystem.specialist.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.specialist.domain.entity.StudyMajorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyMajorRepository extends BaseRepository<StudyMajorEntity>, JpaRepository<StudyMajorEntity, Long> {
    List<StudyMajorEntity> findAllByStudyMajorNameIn(List<String> studyMajorsStr);

    List<StudyMajorEntity> findAllByIdIn(List<Long> ids);
}
