package cn.gmsj.evaluationsystem.expertdesign.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.expertdesign.domain.entity.ExpertSuggestEntity;
import cn.gmsj.evaluationsystem.projectmanage.domain.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpertDesignRepository extends BaseRepository<ExpertSuggestEntity>, JpaRepository<ExpertSuggestEntity, Long> {

   //List<ProjectEntity> findAllById();
}
