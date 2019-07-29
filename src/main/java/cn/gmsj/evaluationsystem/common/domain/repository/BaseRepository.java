package cn.gmsj.evaluationsystem.common.domain.repository;

/**
 * jpa业务基础查询
 * @author Alan
 */
public interface BaseRepository<T> {

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    T findAllById(Long id);
}
