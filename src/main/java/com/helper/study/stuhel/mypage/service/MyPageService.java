package com.helper.study.stuhel.mypage.service;

import com.helper.study.stuhel.book.to.BookTO;
import com.helper.study.stuhel.member.to.MemberTO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

public interface MyPageService {
    MemberTO retrieve(MemberTO memberTO);

    HashMap<String, Integer> changeInfo(MemberTO memberTO, HttpSession session);

    ArrayList<BookTO> bookStatusRetrieve(BookTO bookTO);

    HashMap<String,String> bookCancel(ArrayList<BookTO> bookTOList);

}
