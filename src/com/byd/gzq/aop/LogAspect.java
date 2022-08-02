package com.byd.gzq.aop;

import com.byd.gzq.Customer;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LogAspect {

    private final Logger log = Logger.getLogger("loggeraspect");

    @Before("execution(* com.byd.gzq.servlet.BaseServlet())")
    public void doBefore(){
        log.fatal("asd");
    }

    @AfterReturning(pointcut = "@annotation(customer)",returning = "returnType")
    public void afterReturning(JoinPoint jp, Customer customer,Object returnType){
        log.fatal(customer.value());
    }

    @AfterThrowing(pointcut = "@annotation(customer)",throwing = "e")
    public void doThrowing(JoinPoint jp,Customer customer,Exception e){
        log.fatal(e.getMessage()+"---"+customer.value());
    }
}
