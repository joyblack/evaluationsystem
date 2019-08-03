package cn.gmsj.evaluationsystem.expertinfo.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.DeclareMajorEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.DeclareMajorTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclareMajorTypeRepository extends BaseRepository<DeclareMajorTypeEntity>, JpaRepository<DeclareMajorTypeEntity, Long> {
}
