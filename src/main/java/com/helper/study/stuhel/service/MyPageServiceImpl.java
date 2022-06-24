package com.helper.study.stuhel.service;

import com.helper.study.stuhel.mapper.MemberDAO;
import com.helper.study.stuhel.mapper.MyPageDAO;
import com.helper.study.stuhel.to.MemberTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public MemberTO changeInfo(MemberTO memberTO, String sessionMemberId){
        System.out.println("****************2****************");
        String cha="";
        if(memberTO.getId()!=null || memberTO.getId().trim()!="") {
            cha += " ,ID =" + memberTO.getId();
        }if(memberTO.getName()!=null){
            cha +=" ,NAME ="+memberTO.getName();
        }if(memberTO.getPassword()!=null){
            cha +=" ,PASSWORD ="+memberTO.getPassword();
        }if(memberTO.getBirthday()!=0){
            cha +=" ,BIRTHDAY ="+memberTO.getBirthday();
        }
        System.out.println("cha : "+cha);
        return  myPageDAO.changeInfo(cha, sessionMemberId);
    }

    @Override
    public MemberTO bookInfo(MemberTO memberTO) {
        return null;
    }
}
