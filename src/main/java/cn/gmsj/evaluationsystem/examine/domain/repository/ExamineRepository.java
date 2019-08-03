package cn.gmsj.evaluationsystem.examine.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.examine.domain.entity.ExamineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XiaoWen on 2019/8/3
 */
public interface ExamineRepository extends BaseRepository<ExamineEntity>, JpaRepository<ExamineEntity,Long> {

}
