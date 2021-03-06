package com.helper.study.stuhel.member.controller;

import com.google.gson.GsonBuilder;
import com.helper.study.stuhel.member.to.MemberTO;
import com.helper.study.stuhel.exception.IdNotFoundException;
import com.helper.study.stuhel.exception.PwMissMatchException;
import com.helper.study.stuhel.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/member")
@ResponseBody
public class MemberController {

    // GSON 라이브러리
    private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/sessionCheck") /*세션확인*/
    public int sessionCheck(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("memberId")==null){
            return -1;
        }
        return 0;
    }
    /*로그인*/
    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestParam("loginData") String loginData,HttpServletRequest request){
        System.out.println("loginData = " + loginData);
        HttpSession session = request.getSession();
        HashMap<String, Object> map = new HashMap<>();

        try{
            MemberTO memberTO = gson.fromJson(loginData, MemberTO.class);
            MemberTO member = memberService.login(memberTO);
            System.out.println(" memberController ");

            session.setAttribute("sessionId", session.getId());
            session.setAttribute("memberId", member.getId());
            session.setAttribute("memberName", member.getName());
            session.setMaxInactiveInterval(30*60);
            System.out.println("id");
            System.out.println(session.getAttribute("memberId"));
            System.out.println("name");
            System.out.println(session.getAttribute("memberName"));

        } catch (IdNotFoundException e1) {
            //e1.printStackTrace();
            map.put("errorCode", -1);
            map.put("errorMsg", e1.getMessage());

        } catch (PwMissMatchException e2) {
            //e2.printStackTrace();
            map.put("errorCode", -2);
            map.put("errorMsg", e2.getMessage());

        }
        return map;
    }

    /*로그아웃*/
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("memberId"));
        session.invalidate();
        response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires",0);
        return "index.html";
    }


    /*id중복검사*/
    @GetMapping("/idDoubleCheck")
    public HashMap<String,Integer> idDoubleCheck (@RequestParam("identity") String identity) {
        HashMap<String, Integer> map = new HashMap<>();

        return memberService.idDoubleCheck(identity);
    }


    /*회원가입*/
    @PostMapping("/join")
    public HashMap<String, Integer> join (@RequestParam("joinData") String joinData ){
        System.out.println("joinData = " + joinData);

        MemberTO memberTO = gson.fromJson(joinData, MemberTO.class);
        HashMap<String, Integer> map = new HashMap<>();

        return memberService.memberJoinData(memberTO);
    }

}
