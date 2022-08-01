package com.helper.study.stuhel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;

@Component
public class SessionInterceptor implements AsyncHandlerInterceptor {
    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("SessionInterceptor.preHandle 아니 왜 안되냐고 쉬봡바바바씨이바아차아아");
        if(session.getAttribute("memberId")==null){
            response.sendRedirect("/");
            System.out.println("SessionInterceptor.preHandle 나라라라라라라라 false");
            return false;
        }
        else {System.out.println("SessionInterceptor.preHandle 나라라라라라라라 true");
            return true;
        }
                //AsyncHandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setDateHeader("Expires",-1);
        AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

    }
}
