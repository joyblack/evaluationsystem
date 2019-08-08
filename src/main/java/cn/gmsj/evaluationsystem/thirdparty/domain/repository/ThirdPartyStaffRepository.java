package cn.gmsj.evaluationsystem.thirdparty.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.ThirdPartyEntity;
import cn.gmsj.evaluationsystem.thirdparty.domain.entity.ThirdPartyStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThirdPartyStaffRepository extends BaseRepository<ThirdPartyStaffEntity>, JpaRepository<ThirdPartyStaffEntity, Long> {

}
