package cn.gmsj.evaluationsystem.expertinfo.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.ExpertInfoEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.PositionalTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionalTypeRepository extends BaseRepository<PositionalTypeEntity>, JpaRepository<PositionalTypeEntity, Long> {
}
