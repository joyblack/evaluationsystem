package cn.gmsj.evaluationsystem.specialist.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.specialist.domain.entity.ExpertInfoImageEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by XiaoWen on 2019/8/3
 */
public interface ExpertInfoImageRepository extends BaseRepository<ExpertInfoImageEntity>, JpaRepository<ExpertInfoImageEntity,Long> {
    ExpertInfoImageEntity findAllByUuid(String uuid);
    List<ExpertInfoImageEntity> findAllByUserEntityOrderByCreateTimeDesc(UserEntity userEntity);
}
