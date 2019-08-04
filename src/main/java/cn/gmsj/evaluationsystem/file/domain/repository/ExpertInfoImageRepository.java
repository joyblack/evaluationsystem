package cn.gmsj.evaluationsystem.file.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.file.domain.entity.ExpertInfoImageEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XiaoWen on 2019/8/3
 */
public interface ExpertInfoImageRepository extends BaseRepository<ExpertInfoImageEntity>, JpaRepository<ExpertInfoImageEntity,Long> {
    ExpertInfoImageEntity findAllByUuid(String uuid);
}
