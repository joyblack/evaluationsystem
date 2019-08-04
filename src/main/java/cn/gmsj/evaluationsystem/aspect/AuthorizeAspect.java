package cn.gmsj.evaluationsystem.aspect;

import cn.gmsj.evaluationsystem.config.JwtParamConfig;
import cn.gmsj.evaluationsystem.enums.Token;
import cn.gmsj.evaluationsystem.exception.WafException;
import cn.gmsj.evaluationsystem.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * aop登陆拦截前台
 *
 * @author 13562
 */
@Aspect
@Component
public class AuthorizeAspect {

    @Autowired
    private JwtParamConfig jwtParamConfig;

    @Pointcut("execution(public * cn.gmsj.evaluationsystem.*.web.*.*(..))"
            + "&&!execution(public * cn.gmsj.evaluationsystem.login.web.LoginController.*(..))"
            + "&&!execution(public * cn.gmsj.evaluationsystem.user.web.*(..))"
            + "&&!execution(public * cn.gmsj.evaluationsystem.government.web.*(..))")
    public void auth() {
    }

    @Before("auth()")
    public void doAuth() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        final Object authHeader = request.getHeader(Token.AUTHORIZATION.getName());
        if (authHeader == null) {
            throw new WafException("", "用户尚未登陆", HttpStatus.NOT_ACCEPTABLE);
        }
        final Claims claims = JwtUtil.parseJWT(authHeader.toString(), jwtParamConfig.getBase64Security());
        if (claims == null) {
            throw new WafException("", "用户尚未登陆", HttpStatus.NOT_ACCEPTABLE);
        }
        request.setAttribute(Token.CLAIMS.getName(), claims);
        response.setHeader(Token.AUTHORIZATION.getName(), JwtUtil.createJWT(claims, jwtParamConfig));
    }
}
