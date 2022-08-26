package com.byd.gzq.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Method;

/**
 * @author 4466184
 * @date 2022/8/18 10:13
 */

public class DiyLogger implements MethodReplacer {

    private Object init;

    public void setInit(Object init) {
        this.init = init;
    }

    private static final Logger log  = LogManager.getLogger(DiyLogger.class);
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        log.warn("Successfully replaced, entering......");
        log.warn("Successfully replaced, leaving......");
        return method.invoke(init, objects);
    }
}
