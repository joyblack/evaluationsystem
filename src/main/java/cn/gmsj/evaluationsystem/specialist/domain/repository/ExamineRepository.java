package cn.gmsj.evaluationsystem.specialist.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.specialist.domain.entity.ExamineEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XiaoWen on 2019/8/3
 */
public interface ExamineRepository extends BaseRepository<ExamineEntity>, JpaRepository<ExamineEntity,Long> {

    Page<ExamineEntity> findAll(Specification specification, Pageable pageable);

}
