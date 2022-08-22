package com.helper.study.stuhel.mypage.service;

import com.helper.study.stuhel.mypage.mapper.MyPageMapper;
import com.helper.study.stuhel.book.to.BookTO;
import com.helper.study.stuhel.member.to.MemberTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MyPageServiceImpl implements MyPageService{
    private final MyPageMapper myPageMapper;

    @Autowired
    public MyPageServiceImpl(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }

    @Override
    public MemberTO retrieveMemberInfo(MemberTO memberTO) {
        return myPageMapper.selectMemberInfo(memberTO);
    }

    public HashMap<String,Integer> changeMemberInfo(MemberTO memberTO, HttpSession session){
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
        if(memberTO.getBirth()==0){
            memberTO.setBirth(0);
        }

        myPageMapper.updateMemberInfo(memberTO);
        map.put("errorCode", 1);
        return map;
    }

    @Override
    public ArrayList<BookTO> retrieveBookStatus(BookTO bookTO) {
        System.out.println("book.getBookingDate() = " + bookTO.getBookDate());
        System.out.println("book.getUserId() = " + bookTO.getUserId());
        ArrayList<BookTO> book= myPageMapper.selectBookStatus(bookTO);
        return book;
    }

    public HashMap<String,String> cancelBook(ArrayList<BookTO> bookTOList){
        HashMap<String, String> map = new HashMap<>();
        try{

            for(BookTO bookTO:bookTOList) {
                myPageMapper.deleteBook(bookTO);
            }
            map.put("errorCode","Y");
            System.out.println("map.get(\"errorCode\") = " + map.get("errorCode"));
        }catch (Exception e) {
            map.put("errorCode", "N");
            e.printStackTrace();
        }
        return map;
    }
}
