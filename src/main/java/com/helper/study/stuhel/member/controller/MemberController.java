package com.helper.study.stuhel.member.controller;

import com.google.gson.GsonBuilder;
import com.helper.study.stuhel.common.controller.SessionController;
import com.helper.study.stuhel.member.to.MemberTO;
import com.helper.study.stuhel.exception.IdNotFoundException;
import com.helper.study.stuhel.exception.PwMissMatchException;
import com.helper.study.stuhel.member.service.MemberService;
import org.hibernate.Session;
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
    private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public HashMap<String, String> login(@RequestParam("loginData") String loginData,HttpServletRequest request){
        HttpSession session = request.getSession();
        HashMap<String, String> map = new HashMap<>();
        try{
            MemberTO memberTO = gson.fromJson(loginData, MemberTO.class);
            MemberTO member = memberService.login(memberTO);
            SessionController.loginSession(session,member);
        } catch (IdNotFoundException e1) {
            e1.printStackTrace();
            map.put("errorCd", "Y");
            map.put("errorMsg", e1.getMessage());
        } catch (PwMissMatchException e2) {
            e2.printStackTrace();
            map.put("errorCd", "Y");
            map.put("errorMsg", e2.getMessage());
        }
        return map;
    }
    @GetMapping("/idDoubleCheck")
    public HashMap<String,String> idDoubleCheck (@RequestParam("memberId") String memberId) {
        return memberService.idDoubleCheck(memberId);
    }
    /*회원가입*/
    @PostMapping("/join")
    public HashMap<String, String> memberJoin (@RequestParam("joinData") String memberJoinData ){
        MemberTO memberTO = gson.fromJson(memberJoinData, MemberTO.class);
        return memberService.memberJoin(memberTO);
    }

}
