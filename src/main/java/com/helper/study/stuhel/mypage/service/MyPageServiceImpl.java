package com.helper.study.stuhel.mypage.service;

import com.helper.study.stuhel.common.controller.SessionController;
import com.helper.study.stuhel.mypage.mapper.MyPageMapper;
import com.helper.study.stuhel.home.to.BookTO;
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

    public HashMap<String,String> changeMemberInfo(MemberTO memberTO,HttpSession session){
        HashMap<String, String> map = new HashMap<>();
        map.put("errorCd", "N");
        map.put("errorMsg", "성공");
        if(memberTO.getId().isEmpty()&&memberTO.getBirth()==0&&memberTO.getPassword().isEmpty()&&memberTO.getName().isEmpty()) {
            map.put("errorCd", "Y");
            map.put("errorMsg", "변경할 데이터가 없습니다.");
            return map;
        }
        try {
            myPageMapper.updateMemberInfo(memberTO);

            if(memberTO.getId().isEmpty() || memberTO.getId()==""){
                memberTO.setId((String) session.getAttribute("memberId"));
            }
            memberTO.setId(memberTO.getId());
            memberTO=retrieveMemberInfo(memberTO);
            SessionController.loginSession(session, memberTO);

        }catch (Exception e) {
            map.put("errorCd", "Y");
            map.put("errorMsg", "정보변경 중 데이터 에러발생");
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public ArrayList<BookTO> retrieveBookStatus(BookTO bookTO) {
        System.out.println("bookTO.getBookingDate() = " + bookTO.getBookDate());
        System.out.println("bookTO.getUserId() = " + bookTO.getUserId());
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
        }catch (Exception e) {
            map.put("errorCode", "N");
            e.printStackTrace();
        }
        return map;
    }
}
