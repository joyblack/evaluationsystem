package cn.gmsj.evaluationsystem.thirdparty.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.BusinessLicenceEntity;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.ThirdPartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessLicenceRepository extends BaseRepository<BusinessLicenceEntity>, JpaRepository<BusinessLicenceEntity, Long> {

    BusinessLicenceEntity findAllByUuid(String uuid);
}
