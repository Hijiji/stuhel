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
@RequestMapping("/stuhel/myPage")
@ResponseBody
public class MyPageController {
    private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
    private final MyPageService myPageService;

    @Autowired
    public MyPageController(MyPageService myPageService){
        this.myPageService = myPageService;
    }

    @PostMapping("/retrieveMemberInfo")
    String retrieveMemberInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        HashMap<String, MemberTO> map=new HashMap<>();
        MemberTO memberTO=new MemberTO();
        String result;

        if(session.getAttribute("memberId")==null) {
           result=null;
        }else {
            memberTO.setId((String)session.getAttribute("memberId"));
            memberTO.setName((String)session.getAttribute("memberName"));
            memberTO=myPageService.retrieveMemberInfo(memberTO);
            /*아래 코드 없어도 session 잘 작동하는지 확인하기*/
           /* session.setAttribute("memberName",memberTO.getName());
            session.setAttribute("birthday",memberTO.getBirth());
            session.setAttribute("password",memberTO.getPassword());*/
            result =gson.toJson(memberTO);
        }
        return result;
    }

    @PostMapping("/changeMemberInfo")
    HashMap<String, Integer> changeMemberInfo(HttpServletRequest request, @RequestParam("changeMemberInfo") String changeMemberInfo){
        HttpSession session = request.getSession();
        String sessionMemberId=(String)session.getAttribute("memberId");
        String sessionName=(String)session.getAttribute("memberName");
        MemberTO memberTO = gson.fromJson(changeMemberInfo, MemberTO.class);

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
        if(memberTO.getBirth()==0){
            memberTO.setBirth((int)session.getAttribute("birthday"));
        }

        map=myPageService.changeMemberInfo(memberTO,session);

        return map;
    }
    @PostMapping("/retrieveBookStatus")
    public String retrieveBookStatus(HttpServletRequest request,@RequestParam("bookStatusData") String bookStatusData){
        HttpSession session = request.getSession();
        BookTO book = gson.fromJson(bookStatusData, BookTO.class);
        book.setUserId((String)session.getAttribute("memberId"));
        return gson.toJson(myPageService.retrieveBookStatus(book));
    }

    @PostMapping("/cancelBook")
    public HashMap<String, String> cancelBook(@RequestParam("bookCancelData") String bookCancelData){
        ArrayList<BookTO> bookTOList = gson.fromJson(bookCancelData, new TypeToken<ArrayList<BookTO>>() {}.getType());
        HashMap<String, String> map=myPageService.cancelBook(bookTOList);
        return map;
    }
}
