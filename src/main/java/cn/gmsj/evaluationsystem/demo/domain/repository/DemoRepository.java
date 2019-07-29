package cn.gmsj.evaluationsystem.demo.domain.repository;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.demo.domain.entity.DemoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 13562
 */
public interface DemoRepository extends BaseRepository<DemoEntity>, JpaRepository<DemoEntity, Long> {
    /**
     * 分页条件查询
     *
     * @param specification
     * @param pageable
     * @return
     */
    Page<DemoEntity> findAll(Specification specification, Pageable pageable);
}
