package com.byd.gzq.aop;

import com.byd.gzq.utils.GZQ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Leonard
 * @date 2022/8/17 16:17
 */

@Aspect
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
        Object res = null;
        Object[] args = jpc.getArgs();
        Object var0 = args[0];
        if(!(var0 instanceof Integer) || (Integer)var0<0){
            log.fatal("参数为负数,错误!");
            throw new ArithmeticException("参数为负数,错误!");
        }
        try {
            res = jpc.proceed(jpc.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return res;
    }

    @After(value = "@annotation(c)")
    public void doAfter(JoinPoint jp, GZQ c){
        log.info("{} 执行结束...",jp.getSignature().getName());
    }

    @AfterThrowing(value = "@annotation(c)",throwing = "e")
    public void doThrow(JoinPoint jpc,GZQ c,ArithmeticException e){
        log.warn("{} 抛出异常",e.getMessage());
    }

}
