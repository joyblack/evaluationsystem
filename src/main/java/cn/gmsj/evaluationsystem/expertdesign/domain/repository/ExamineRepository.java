package cn.gmsj.evaluationsystem.expertdesign.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.expertdesign.domain.entity.ExamineEntity;
import cn.gmsj.evaluationsystem.expertdesign.domain.entity.InviteEntity;
import cn.gmsj.evaluationsystem.projectmanage.domain.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamineRepository extends BaseRepository<ExamineEntity>, JpaRepository<ExamineEntity, Long> {



    /**
     *通过项目名查询审查记录
     */

    List<ExamineEntity> findAllByProject(String projectName);
}
