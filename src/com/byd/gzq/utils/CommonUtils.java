package com.byd.gzq.utils;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {
    public static boolean isStaticReq(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        return requestURI.indexOf(".jpg")>0||requestURI.indexOf(".js")>0||requestURI.indexOf(".html")>0
                ||requestURI.indexOf(".png")>0||requestURI.indexOf(".jpeg")>0||requestURI.indexOf(".gif")>0
                ||requestURI.indexOf(".css")>0||requestURI.indexOf(".xhtml")>0||requestURI.indexOf(".bmp")>0;
    }
}
