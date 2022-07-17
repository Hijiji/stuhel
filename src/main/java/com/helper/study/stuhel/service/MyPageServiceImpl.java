package com.helper.study.stuhel.service;

import com.helper.study.stuhel.mapper.MyPageDAO;
import com.helper.study.stuhel.to.BookTO;
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
    public ArrayList<BookTO> bookStatusRetrieve(BookTO bookTO) {
        System.out.println("----------------------------------MyPageServiceImpl.bookStatusRetrieve--------------------------");
        System.out.println("book.getBookingDate() = " + bookTO.getBookingDate());
        System.out.println("book.getUserId() = " + bookTO.getUserId());
        ArrayList<BookTO> a=myPageDAO.bookStatusRetrieve(bookTO);
        System.out.println("222222222222222222222222MyPageServiceImpl.bookStatusRetrieve2222222222222222222222222222222222222");
        for(BookTO b:a) {
            System.out.println("b.getBookingDate() = " + b.getBookingDate());
            System.out.println("b.getBookingTime() = " + b.getBookingTime());
            System.out.println("b.getBookingTime() = " + b.getBookingTime());
        }
        return a;
    }

    public HashMap<String,String> bookCancel(ArrayList<BookTO> bookTOList){
        HashMap<String, String> map = new HashMap<>();
        try{
            System.out.println("------------------------------------MyPageServiceImpl.bookCancel-------------------------------------");
            for(BookTO bookTO:bookTOList) {
                myPageDAO.bookCancel(bookTO);
            }
            map.put("errorCode","Y");
            System.out.println("map.get(\"errorCode\") = " + map.get("errorCode"));
        }catch (Exception e) {
            map.put("errorCode", "N");
            System.out.println("map.get(\"errorCode\") = " + map.get("errorCode"));
            e.printStackTrace();
        }
        return map;
    }
}
