package cn.gmsj.evaluationsystem.utils;

import cn.gmsj.evaluationsystem.enums.Token;
import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 13562
 */
public class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    /**
     * 获取登陆用户信息
     *
     * @param request
     * @return
     */
    public static UserEntity getUser(HttpServletRequest request) {
        try {
            Claims claims = (Claims) request.getAttribute(Token.CLAIMS.getName());
            return JSONUtil.toBean(JSONUtil.toJsonStr(claims.get(Token.USER.getName())), UserEntity.class);
        } catch (Exception e) {
            logger.info("无法从token中取得用户信息");
            return null;
        }
    }

    /**
     * 获取登陆用户id
     *
     * @param request
     * @return
     */
    public static Long getUserId(HttpServletRequest request) {
        //测试返回
        return 1L;
//        try {
//            Claims claims = (Claims) request.getAttribute(Token.CLAIMS.getName());
//            return JSONUtil.toBean(JSONUtil.toJsonStr(claims.get(Token.USER.getName())), UserEntity.class).getId();
//        } catch (Exception e) {
//            logger.info("无法从token中取得用户信息");
//            return null;
//        }
    }

}
