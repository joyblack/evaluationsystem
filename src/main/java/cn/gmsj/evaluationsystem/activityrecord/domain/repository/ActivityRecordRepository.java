package cn.gmsj.evaluationsystem.activityrecord.domain.repository;

import cn.gmsj.evaluationsystem.activityrecord.domain.entity.ActivityRecordEntity;
import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by XiaoWen on 2019/8/3
 */
public interface ActivityRecordRepository extends BaseRepository<ActivityRecordEntity>, JpaRepository<ActivityRecordEntity, Long> {

    /**
     * 分页查询
     * @param specification
     * @param pageable
     * @return
     */
    Page<ActivityRecordEntity> findAll(Specification specification, Pageable pageable);

}
