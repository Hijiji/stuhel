package com.helper.study.stuhel.mypage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.helper.study.stuhel.mypage.service.MyPageService;
import com.helper.study.stuhel.home.to.BookTO;
import com.helper.study.stuhel.member.to.MemberTO;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/retrieveMemberInfo")
    String retrieveMemberInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberTO memberTO=new MemberTO();

        if(session.getAttribute("memberId")==null) {
           return null;
        }else {
            memberTO.setId((String)session.getAttribute("memberId"));
            System.out.println("memberTO.getId() = " + memberTO.getId());
            memberTO.setName((String)session.getAttribute("memberName"));
            System.out.println("memberTO.getName() = " + memberTO.getName());
            memberTO=myPageService.retrieveMemberInfo(memberTO);
        }
        return gson.toJson(memberTO);
    }
    @DeleteMapping("/deleteMember")
    HashMap<String,String> deleteMember(HttpServletRequest request, @RequestParam("deleteMemberInfo") String deleteMemberInfo){
        HttpSession session = request.getSession();
        MemberTO memberTO = gson.fromJson(deleteMemberInfo, MemberTO.class);
        HashMap<String, String> map=myPageService.deleteMemberInfo(memberTO);
        return map;
    }
    @PutMapping("/changeMemberInfo")
    HashMap<String, String> changeMemberInfo(HttpServletRequest request, @RequestParam("changeMemberInfo") String changeMemberInfo){
        HttpSession session = request.getSession();
        MemberTO memberTO = gson.fromJson(changeMemberInfo, MemberTO.class);
        //회원 ID가 변경될 경우도 있기 때문에, session에 저장된 회원Id를 UPDATE쿼리의 조건으로 두기위해 셋팅함
        memberTO.setSessionId((String)session.getAttribute("memberId"));
        HashMap<String, String> map=myPageService.changeMemberInfo(memberTO,session);

        return map;
    }
    @GetMapping("/retrieveBookStatus")
    public String retrieveBookStatus(HttpServletRequest request,@RequestParam("bookStatusData") String bookStatusData){
        HttpSession session = request.getSession();
        BookTO book = gson.fromJson(bookStatusData, BookTO.class);
        book.setUserId((String)session.getAttribute("memberId"));
        return gson.toJson(myPageService.retrieveBookStatus(book));
    }
    @DeleteMapping("/cancelBook")
    public HashMap<String, String> cancelBook(@RequestParam("cancelBookData") String bookCancelData){
        ArrayList<BookTO> bookTOList = gson.fromJson(bookCancelData, new TypeToken<ArrayList<BookTO>>() {}.getType());
        HashMap<String, String> map=myPageService.cancelBook(bookTOList);
        return map;
    }
}
