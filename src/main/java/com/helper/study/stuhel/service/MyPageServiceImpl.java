package com.helper.study.stuhel.service;

import com.helper.study.stuhel.mapper.MemberDAO;
import com.helper.study.stuhel.mapper.MyPageDAO;
import com.helper.study.stuhel.to.MemberTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

    public MemberTO changeInfo(MemberTO memberTO, HttpSession session){
        System.out.println("****************2****************");
        String id="", password="", name=""; int birthDay=0;

        if(memberTO.getId()!=null || memberTO.getId().trim()!="") {
            id = (String)session.getAttribute("memberId");
        }else id =  memberTO.getId();
        if(memberTO.getName()!=null){
            name = (String)session.getAttribute("memberName");
        }else name =  memberTO.getName();
        if(memberTO.getPassword()!=null){
            password = (String)session.getAttribute("memberPassword");
        }else password = memberTO.getPassword();
        if(memberTO.getBirthday()!=0){
            birthDay = memberTO.getBirthday();
        }else birthDay =0;
                //(int)session.getAttribute("memberBirthDay");

        return  myPageDAO.changeInfo(id, name, password, birthDay);
    }

    @Override
    public MemberTO bookInfo(MemberTO memberTO) {
        return null;
    }
}
