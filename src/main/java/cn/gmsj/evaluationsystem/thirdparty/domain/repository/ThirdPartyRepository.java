package cn.gmsj.evaluationsystem.thirdparty.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.ThirdPartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThirdPartyRepository extends BaseRepository<ThirdPartyEntity>, JpaRepository<ThirdPartyEntity, Long> {
    ThirdPartyEntity findAllByUserId(Long userId);

    List<ThirdPartyEntity> findAllByPhoneAndIdNot(String phone, Long id);
}
