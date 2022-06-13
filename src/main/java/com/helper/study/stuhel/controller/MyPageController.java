package com.helper.study.stuhel.controller;

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

    private final MyPageService myPageService;

    @Autowired
    public MyPageController(MyPageService myPageService){
        this.myPageService = myPageService;
    }

    @PostMapping("/retrieve")
    HashMap<String, MemberTO> retrieve(HttpServletRequest request) {
        HttpSession session = request.getSession();
        HashMap<String, MemberTO> map=new HashMap<>();
        MemberTO memberTO=new MemberTO();
        if(session.getAttribute("memberId")==null) {
            map.put("memberId", null);
        }else {
            memberTO.setId((String)session.getAttribute("memberId"));
            memberTO=myPageService.retrieve(memberTO);  //뒷단돌려서 받아온 member 정보 gsom으로 역직렬화 하는법 찾기
        }
        return map;
    }

}
