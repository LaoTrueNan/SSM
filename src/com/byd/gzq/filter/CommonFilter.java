package com.byd.gzq.filter;

import com.byd.gzq.utils.CommonUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommonFilter extends HttpFilter {

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

        chain.doFilter(req,resp);
    }
}
