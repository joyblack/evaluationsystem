package cn.gmsj.evaluationsystem.projectmanage.domain.repository;



import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.projectmanage.domain.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liuwei
 */
public interface ProjectRepository extends BaseRepository<ProjectEntity>, JpaRepository<ProjectEntity, Long> {

}
