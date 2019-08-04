package cn.gmsj.evaluationsystem.user.service;

import cn.gmsj.evaluationsystem.common.constant.RedisKey;
import cn.gmsj.evaluationsystem.common.service.IRedisService;
import cn.gmsj.evaluationsystem.user.domain.model.UserPassordResetNote;
import org.springframework.stereotype.Service;

/**
 * @author 13562
 */
@Service
public class UserPasswordResetRedisService extends IRedisService<UserPassordResetNote> {
    @Override
    protected String getRedisKey() {
        return RedisKey.USER_PASSWPRD_RESET;
    }
}
