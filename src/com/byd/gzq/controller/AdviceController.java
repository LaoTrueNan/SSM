package com.byd.gzq.controller;

import com.byd.gzq.utils.Exception.ServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @author 4466184
 * @date 2022/9/21 15:55
 */

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(NullPointerException.class)
    public String noSuchResult(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        return "nothing found for "+request.getRequestURI()+"/"+ parameterMap.get("id")[0];
    }

    @ExceptionHandler(ArithmeticException.class)
    public String arithmetic(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        int a = 0;
        return ("处理请求" + request.getRequestURI() + "时计算错误");
    }

    @ExceptionHandler(ServiceException.class)
    public void service(HttpServletResponse resp,ServiceException e) throws IOException{
        resp.getWriter().write(e.getMessage());
    }
}