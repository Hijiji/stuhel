package com.helper.study.stuhel.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helper.study.stuhel.service.MyPageService;
import com.helper.study.stuhel.to.MemberTO;
import org.apache.catalina.session.StandardSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/myPage")
@ResponseBody
public class MyPageController {
    private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
    private final MyPageService myPageService;

    @Autowired
    public MyPageController(MyPageService myPageService){
        this.myPageService = myPageService;
    }

    @PostMapping("/retrieve")
    String retrieve(HttpServletRequest request) {
        HttpSession session = request.getSession();
        HashMap<String, MemberTO> map=new HashMap<>();
        MemberTO memberTO=new MemberTO();
        String result;

        if(session.getAttribute("memberId")==null) {
            System.out.println(session.getAttribute("memberId"));
           result=null;
        }else {
            System.out.println(session.getAttribute("memberId"));
            System.out.println(session.getAttribute("memberName"));
            memberTO.setId((String)session.getAttribute("memberId"));
            memberTO.setName((String)session.getAttribute("memberName"));
            memberTO=myPageService.retrieve(memberTO);  //뒷단돌려서 받아온 member 정보 gson으로 역직렬화 하는법 찾기

            result =gson.toJson(memberTO);
            //session.setAttribute("");
            System.out.println(result);
        }
        return result;
    }

    @PostMapping("/changeInfo")
    HashMap<String, Integer> changeInfo(HttpServletRequest request, @RequestParam("changeInfo") String changeInfo){
        HttpSession session = request.getSession();
        String sessionMemberId=(String)session.getAttribute("memberId");
        String sessionName=(String)session.getAttribute("memberName");
        System.out.println("sessionMemberId = " + sessionMemberId);
        MemberTO memberTO = gson.fromJson(changeInfo, MemberTO.class);

        HashMap<String, Integer> map = new HashMap<>();
        memberTO.setSessionId(sessionMemberId);
        if(memberTO.getId().isEmpty()|| memberTO.getId()==null || memberTO.getId().trim()=="") {
            memberTO.setId(sessionMemberId);
        }
        if(memberTO.getName().isEmpty()|| memberTO.getName()==null || memberTO.getName().trim()==""){
            memberTO.setName(sessionName);
        }
        if(memberTO.getPassword().isEmpty()||memberTO.getPassword()==null){
            memberTO.setPassword((String)session.getAttribute("memberPassword"));
        }
        if(memberTO.getBirthday()==0){
            memberTO.setBirthday(0);
        }
        System.out.println("memberTO.getId() = " + memberTO.getId());
        System.out.println("memberTO.getName() = " + memberTO.getName());
        System.out.println("memberTO.getPass() = " + memberTO.getPassword());
        System.out.println("memberTO.getBirth() = " + memberTO.getBirthday());
        System.out.println("memberTO.getSessionId() = " + memberTO.getSessionId());
        System.out.println("****************1****************");
        myPageService.changeInfo(memberTO,session);

        return null;
    }
}
