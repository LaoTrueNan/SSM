package com.byd.gzq.aop;

import com.byd.gzq.utils.GZQ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author 4466184
 * @date 2022/8/17 16:17
 */

//@Aspect
@Component
public class LogAspect implements Serializable {

    private static final long serialVersionUID = 1L;

    private final transient Logger log = LogManager.getLogger(LogAspect.class);

    @Pointcut(value = "execution(* com.byd.gzq.bean.Person.calNameLength())")
    public void base() {

    }


//    @Before("base()")
//    public void doBefore(JoinPoint jpc) {
//        MethodSignature method = (MethodSignature) jpc.getSignature();
//        log.fatal(method.getMethod().getAnnotation(GZQ.class).value());
//    }


    @Around("@annotation(a)")
    public Object doAround(ProceedingJoinPoint jpc, GZQ a)  {
        log.fatal(a.value());
        MethodSignature signature = (MethodSignature) jpc.getSignature();
        GZQ annotation = signature.getMethod().getAnnotation(GZQ.class);
        log.warn(a);
        log.warn(annotation);
        Object res = null;
        try {
            res = jpc.proceed(jpc.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return res;
    }

}
