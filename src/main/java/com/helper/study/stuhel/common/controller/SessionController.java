package com.helper.study.stuhel.common.controller;

import com.helper.study.stuhel.member.to.MemberTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
@ResponseBody
public class SessionController {

    @GetMapping("/sessionCheck") /*세션확인*/
    public int sessionCheck(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("memberId")==null){
            return -1;
        }
        return 0;
    }

    public static void loginSession(HttpSession session, MemberTO member){
        session.setAttribute("sessionId", session.getId());
        session.setAttribute("memberId", member.getId());
        session.setAttribute("memberName", member.getName());
        session.setMaxInactiveInterval(30*60);

    }

    @DeleteMapping("/logout")/*로그아웃*/
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("memberId"));
        session.invalidate();
        response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setDateHeader("Expires",-1);
        return "/";
    }
}
