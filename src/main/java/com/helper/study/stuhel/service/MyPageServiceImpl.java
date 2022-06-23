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

        return  myPageDAO.changeInfo(memberTO, sessionMemberId);
    }

    @Override
    public MemberTO bookInfo(MemberTO memberTO) {
        return null;
    }
}
