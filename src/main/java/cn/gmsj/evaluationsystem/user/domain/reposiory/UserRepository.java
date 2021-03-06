package cn.gmsj.evaluationsystem.user.domain.reposiory;

import cn.gmsj.evaluationsystem.common.domain.repository.BaseRepository;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Administrator
 */
public interface UserRepository extends BaseRepository<UserEntity>, JpaRepository<UserEntity, Long> {


    /**
     * 检查社会信用代码是否被用过
     *
     * @param socialCreditCode
     * @return
     */
    UserEntity findAllBySocialCreditCode(String socialCreditCode);

    /**
     * @param idNumber
     * @return
     */
    UserEntity findAllByIdNumber(String idNumber);

    /**
     * 查询手机号重复
     *
     * @param phone
     * @param id
     * @return
     */
    UserEntity findAllByPhoneAndIdNot(String phone, Long id);

    /**
     * 查询身份证重复
     *
     * @param idNumber
     * @param id
     * @return
     */
    UserEntity findAllByIdNumberAndIdNot(String idNumber, Long id);

    /**
     * 查询单位名称重复
     *
     * @param unitName
     * @param id
     * @return
     */
    UserEntity findAllByUnitNameAndIdNot(String unitName, Long id);

    /**
     * 查询社会信用代码重复
     *
     * @param socialCreditCode
     * @param id
     * @return
     */
    UserEntity findAllBySocialCreditCodeAndIdNot(String socialCreditCode, Long id);

    /**
     * 分页条件查询
     *
     * @param specification
     * @param pageable
     * @return
     */
    Page<UserEntity> findAll(Specification specification, Pageable pageable);

    /**
     * 通过手机号查询用户
     *
     * @param phone
     * @return
     */
    UserEntity findAllByPhone(String phone);

    /**
     * 通过手机号密码查询用户
     *
     * @param phone
     * @param password
     * @return
     */
    UserEntity findAllByPhoneAndPassword(String phone, String password);

    /**
     * 通过身份证号和密码查询用户
     *
     * @param idNumber
     * @param password
     * @return
     */
    UserEntity findAllByIdNumberAndPassword(String idNumber, String password);

    /**
     * 通过社会信用代码和密码查询用户
     *
     * @param socialCreditCode
     * @param password
     * @return
     */
    UserEntity findAllBySocialCreditCodeAndPassword(String socialCreditCode, String password);
}
