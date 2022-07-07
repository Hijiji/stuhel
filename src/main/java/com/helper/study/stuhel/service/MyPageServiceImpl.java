package com.helper.study.stuhel.service;

import com.helper.study.stuhel.mapper.MyPageDAO;
import com.helper.study.stuhel.to.MemberTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MyPageServiceImpl implements MyPageService{
    private final MyPageDAO myPageDAO;

    @Autowired
    public MyPageServiceImpl(MyPageDAO myPageDAO) {
        this.myPageDAO = myPageDAO;
    }

    @Override
    public MemberTO retrieve(MemberTO memberTO) {
        return myPageDAO.retrieve(memberTO);
    }

    public HashMap<String,Integer> changeInfo(MemberTO memberTO, HttpSession session){
        HashMap<String, Integer> map = new HashMap<>();
        String sessionId=(String)session.getAttribute("memberId");
        memberTO.setSessionId(sessionId);
        if(memberTO.getId()==null) {
            memberTO.setId(sessionId);
        }
        if(memberTO.getName()==null){
            memberTO.setName((String)session.getAttribute("memberName"));
        }
        if(memberTO.getPassword()==null){
            memberTO.setPassword((String)session.getAttribute("memberPassword"));
        }
        if(memberTO.getBirthday()==0){
            memberTO.setBirthday(0);
        }

        myPageDAO.changeInfo(memberTO);
        map.put("errorCode", 1);
        return map;
    }

    @Override
    public MemberTO bookInfo(MemberTO memberTO) {
        return null;
    }

    @Override
    public ArrayList<Integer> bookDateSearch(MemberTO member) {
        return myPageDAO.bookDateSearch(member);
    }
}
