package cn.gmsj.evaluationsystem.expertdesign.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.expertdesign.domain.entity.ExamineEntity;
import cn.gmsj.evaluationsystem.expertdesign.domain.entity.InviteEntity;

import cn.gmsj.evaluationsystem.projectmanage.domain.entity.ProjectEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.gmsj.evaluationsystem.userreg.domain.entity.UserRegEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InviteRepository extends BaseRepository<InviteEntity>, JpaRepository<InviteEntity, Long> {

    /***
     * 分页条件查询
     * @param specification
     * @param pageable
     * @return
     */
    Page<InviteEntity> findAll(Specification specification, Pageable pageable);

    /**
     * 通过邀请用户查询邀请记录
     *
     * @param
     * @return
     */
    Page<InviteEntity> findAllByUser(UserRegEntity userReg, Pageable pageable);

}
