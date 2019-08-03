package cn.gmsj.evaluationsystem.user.domain.reposiory;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.user.domain.entity.UserImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XiaoWen on 2019/8/3
 */
public interface UserImageRepository extends
        BaseRepository<UserImageEntity>, JpaRepository<UserImageEntity,Long> {
}
