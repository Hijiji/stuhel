package com.helper.study.stuhel.mypage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.helper.study.stuhel.mypage.service.MyPageService;
import com.helper.study.stuhel.book.to.BookTO;
import com.helper.study.stuhel.member.to.MemberTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        System.out.println("MyPageController - retrieve");
        HttpSession session = request.getSession();
        HashMap<String, MemberTO> map=new HashMap<>();
        MemberTO memberTO=new MemberTO();
        String result;

        if(session.getAttribute("memberId")==null) {
           result=null;
        }else {
            memberTO.setId((String)session.getAttribute("memberId"));
            memberTO.setName((String)session.getAttribute("memberName"));
            memberTO=myPageService.retrieve(memberTO);  //뒷단돌려서 받아온 member 정보 gson으로 역직렬화 하는법 찾기
            session.setAttribute("memberName",memberTO.getName());
            session.setAttribute("birthday",memberTO.getBirth());
            session.setAttribute("password",memberTO.getPassword());
            result =gson.toJson(memberTO);
        }
        return result;
    }

    @PostMapping("/changeInfo")
    HashMap<String, Integer> changeInfo(HttpServletRequest request, @RequestParam("changeInfo") String changeInfo){
        System.out.println("MyPageController - changeInfo");
        HttpSession session = request.getSession();
        String sessionMemberId=(String)session.getAttribute("memberId");
        String sessionName=(String)session.getAttribute("memberName");
        System.out.println("changeInfo = " + changeInfo);
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
            memberTO.setPassword((String)session.getAttribute("password"));
        }
        System.out.println("memberTO.getBirthday() = " + memberTO.getBirth());
        if(memberTO.getBirth()==0){
            memberTO.setBirth((int)session.getAttribute("birthday"));
        }
        System.out.println("memberTO.getBirthday() = " + memberTO.getBirth());

        map=myPageService.changeInfo(memberTO,session);

        return map;
    }
    @PostMapping("/bookStatusRetrieve")
    public String bookStatusRetrieve(HttpServletRequest request,@RequestParam("bookStatusData") String bookStatusData){
        HttpSession session = request.getSession();
        BookTO book = gson.fromJson(bookStatusData, BookTO.class);
        book.setUserId((String)session.getAttribute("memberId"));
        return gson.toJson(myPageService.bookStatusRetrieve(book));
    }

    @PostMapping("/bookCancel")
    public HashMap<String, String> bookCancel(@RequestParam("bookCancelData") String bookCancelData){
        ArrayList<BookTO> bookTOList = gson.fromJson(bookCancelData, new TypeToken<ArrayList<BookTO>>() {}.getType());
        HashMap<String, String> map=myPageService.bookCancel(bookTOList);
        return map;
    }
}
