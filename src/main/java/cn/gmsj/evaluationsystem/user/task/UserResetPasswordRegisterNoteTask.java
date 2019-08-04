package cn.gmsj.evaluationsystem.user.task;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.user.domain.model.UserPassordResetNote;
import cn.gmsj.evaluationsystem.user.service.UserPasswordResetRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Administrator
 */
@Component
public class UserResetPasswordRegisterNoteTask {

    private static final Logger logger = LoggerFactory.getLogger(UserResetPasswordRegisterNoteTask.class);

    @Autowired
    private UserPasswordResetRedisService userPasswordResetRedisService;

    /**
     * 间隔1秒
     */
    @Scheduled(fixedRate = 1000)
    public void sendNote() {
        List<UserPassordResetNote> userPassordResetNotes = userPasswordResetRedisService.getAll();
        for (UserPassordResetNote userPassordResetNote : userPassordResetNotes) {
            if (!userPassordResetNote.getSend()) {
                logger.info("类型:重置密码", "");
                logger.info("手机号:" + userPassordResetNote.getPhone());
                logger.info("验证码:" + userPassordResetNote.getAuthCode());
                userPassordResetNote.setSend(true);
                userPasswordResetRedisService.put(userPassordResetNote.getPhone(), userPassordResetNote, SystemConstant.NOTE_VALID_TIME);
            }
        }
    }
}
