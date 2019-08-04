package cn.gmsj.evaluationsystem.expertinfo.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.ExpertInfoEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.enums.ExpertInfoType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpertInfoRepository extends BaseRepository<ExpertInfoEntity>, JpaRepository<ExpertInfoEntity, Long> {
    List<ExpertInfoEntity> findAllByIdCardAndIdNot(String idCard,Long id);

    List<ExpertInfoEntity> findAllByPhoneAndIdNot(String phone,Long id);

    ExpertInfoEntity findAllByIdCardAndExpertInfoType(String idCard, ExpertInfoType expertInfoType);

    ExpertInfoEntity findAllByIdCard(String idCard);

    /**
     * 分页模糊查询
     *
     * @param specification
     * @param pageable
     * @return
     */
    Page<ExpertInfoEntity> findAll(Specification specification, Pageable pageable);

}
