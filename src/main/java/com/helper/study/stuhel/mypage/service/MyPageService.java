package com.helper.study.stuhel.mypage.service;

import com.helper.study.stuhel.home.to.BookTO;
import com.helper.study.stuhel.member.to.MemberTO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

public interface MyPageService {
    MemberTO retrieveMemberInfo(MemberTO memberTO);

    HashMap<String, String> deleteMemberInfo(MemberTO memberTO);

    HashMap<String, String> changeMemberInfo(MemberTO memberTO,HttpSession session);

    ArrayList<BookTO> retrieveBookStatus(BookTO bookTO);

    HashMap<String,String> cancelBook(ArrayList<BookTO> bookTOList);

}
