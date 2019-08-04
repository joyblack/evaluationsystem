package cn.gmsj.evaluationsystem.user.service;

import cn.gmsj.evaluationsystem.common.constant.RedisKey;
import cn.gmsj.evaluationsystem.common.service.IRedisService;
import cn.gmsj.evaluationsystem.user.domain.model.UserRegisterNote;
import org.springframework.stereotype.Service;

/**
 * @author 13562
 */
@Service
public class UserRegisterNoteRedisService extends IRedisService<UserRegisterNote> {

    @Override
    protected String getRedisKey() {
        return RedisKey.USER_REGISTER;
    }
}
