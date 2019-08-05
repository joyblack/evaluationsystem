package cn.gmsj.evaluationsystem.governmentuser.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.governmentuser.domain.entity.GovernmentUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XiaoWen on 2019/8/4
 */
public interface GovernmentUserRepository extends BaseRepository<GovernmentUserEntity>, JpaRepository<GovernmentUserEntity,Long> {

    GovernmentUserEntity findAllByNameAndIdNot(String name,Long id);

    GovernmentUserEntity findAllByPhoneAndIdNot(String phone,Long id);
}
