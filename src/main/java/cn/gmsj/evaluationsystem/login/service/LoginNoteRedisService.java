package cn.gmsj.evaluationsystem.login.service;

import cn.gmsj.evaluationsystem.common.constant.RedisKey;
import cn.gmsj.evaluationsystem.common.service.IRedisService;
import cn.gmsj.evaluationsystem.login.domain.model.LoginNote;
import org.springframework.stereotype.Service;

/**
 * @author 13562
 */
@Service
public class LoginNoteRedisService extends IRedisService<LoginNote> {
    @Override
    protected String getRedisKey() {
        return RedisKey.USER_LOGIN;
    }
}
