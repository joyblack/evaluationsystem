package cn.gmsj.evaluationsystem.userreg.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.userreg.domain.entity.UserRegEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegRepository extends BaseRepository<UserRegEntity>, JpaRepository<UserRegEntity, Long> {

    Page<UserRegEntity> findAll(Specification specification, Pageable pageable);


}
