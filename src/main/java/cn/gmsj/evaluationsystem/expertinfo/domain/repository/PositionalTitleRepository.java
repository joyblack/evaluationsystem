package cn.gmsj.evaluationsystem.expertinfo.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.PositionalTitleEntity;
import cn.gmsj.evaluationsystem.expertinfo.domain.entity.PositionalTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionalTitleRepository extends BaseRepository<PositionalTitleEntity>, JpaRepository<PositionalTitleEntity, Long> {
}
