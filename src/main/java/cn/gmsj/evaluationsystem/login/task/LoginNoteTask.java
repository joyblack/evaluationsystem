package cn.gmsj.evaluationsystem.login.task;

import cn.gmsj.evaluationsystem.common.constant.SystemConstant;
import cn.gmsj.evaluationsystem.login.domain.model.LoginNote;
import cn.gmsj.evaluationsystem.login.service.LoginNoteRedisService;
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
public class LoginNoteTask {

    private static final Logger logger = LoggerFactory.getLogger(LoginNoteTask.class);

    @Autowired
    private LoginNoteRedisService loginNoteRedisService;

    /**
     * 间隔1秒
     */
    @Scheduled(fixedRate = 1000)
    public void sendNote() {
        List<LoginNote> loginNotes = loginNoteRedisService.getAll();
        for (LoginNote loginNote : loginNotes) {
            if (!loginNote.getSend()) {
                logger.info("类型:登陆", "");
                logger.info("手机号:" + loginNote.getPhone());
                logger.info("验证码:" + loginNote.getAuthCode());
                loginNote.setSend(true);
                loginNoteRedisService.put(loginNote.getPhone(), loginNote, SystemConstant.NOTE_VALID_TIME);
            }
        }

    }
}
