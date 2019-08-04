package cn.gmsj.evaluationsystem.user.task;

import cn.gmsj.evaluationsystem.user.domain.model.UserRegisterNote;
import cn.gmsj.evaluationsystem.user.service.UserRegisterNoteRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 13562
 */
@Component
public class UserRegisterNoteTask {

    private static final Logger logger = LoggerFactory.getLogger(UserRegisterNoteTask.class);

    @Autowired
    private UserRegisterNoteRedisService userRegisterNoteRedisService;

    /**
     * 间隔1秒
     */
    @Scheduled(fixedRate = 1000)
    public void sendNote() {
        List<UserRegisterNote> userRegisterNotes = userRegisterNoteRedisService.getAll();
        for (UserRegisterNote userRegisterNote : userRegisterNotes) {
            if (!userRegisterNote.getSend()) {
                logger.info("手机号:" + userRegisterNote.getPhone());
                logger.info("验证码:" + userRegisterNote.getAuthCode());
                userRegisterNote.setSend(true);
                userRegisterNoteRedisService.put(userRegisterNote.getPhone(), userRegisterNote, 1800);
            }
        }
    }
}
