package cn.gmsj.evaluationsystem.invite.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.invite.domain.entity.ProjectInviteEntity;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 13562
 */
public interface ProjectInviteRepository extends BaseRepository<ProjectInviteEntity>, JpaRepository<ProjectInviteEntity, Long> {
    /***
     * 分页条件查询
     * @param specification
     * @param pageable
     * @return
     */
    Page<ProjectInviteEntity> findAll(Specification specification, Pageable pageable);

    /**
     * 通过邀请用户查询邀请记录
     *
     * @param user
     * @return
     */
    Page<ProjectInviteEntity> findAllByUser(UserEntity user, Pageable pageable);
}
