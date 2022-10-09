package com.byd.gzq.filter;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class CommonFilter extends HttpFilter {
    private final Logger log = LogManager.getLogger(CommonFilter.class);

    /**
     * 1.change encoding\t 2.check if static resources request
     * @param req
     * @param resp
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        log.info(req.getRequestURL());
        // 输出参数名称,测试中文参数名
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            log.info("参数名称:"+names.nextElement());
        }
        resp.setContentType("text/plain;charset=utf-8");
        resp.setContentType("text/html;charset=utf-8");
        chain.doFilter(req,resp);
    }
}
