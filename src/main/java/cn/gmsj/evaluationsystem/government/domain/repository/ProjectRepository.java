package cn.gmsj.evaluationsystem.government.domain.repository;



import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.government.domain.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author liuwei
 */
public interface ProjectRepository extends BaseRepository<ProjectEntity>, JpaRepository<ProjectEntity, Long> {

    /**
     * 按项目名查取
     * @param projectName
     * @param
     * @return
     */
    List<ProjectEntity> findOneByProjectName(String  projectName);

}
