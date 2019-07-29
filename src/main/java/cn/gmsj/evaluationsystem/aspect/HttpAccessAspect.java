package cn.gmsj.evaluationsystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 13562
 */
@Aspect
@Component
public class HttpAccessAspect {


   private static final Logger logger = LoggerFactory.getLogger(HttpAccessAspect.class);

    @Pointcut("execution(public * cn.gmsj.evaluationsystem.*.web.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doLog(JoinPoint joinPoint) {
        // 记录请求地址，请求形式，请求ip，请求目标方法，请求参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url={}", request.getRequestURI());
        logger.info("method={}", request.getMethod());
        logger.info("ip={}", request.getRemoteAddr());
        logger.info("class_name={}",
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("args={}", joinPoint.getArgs());

    }
}
