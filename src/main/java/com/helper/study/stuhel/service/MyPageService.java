package com.helper.study.stuhel.service;

import com.helper.study.stuhel.to.BookTO;
import com.helper.study.stuhel.to.MemberTO;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

public interface MyPageService {
    MemberTO retrieve(MemberTO memberTO);

    HashMap<String, Integer> changeInfo(MemberTO memberTO, HttpSession session);

    MemberTO bookInfo(MemberTO MemberTO);

    ArrayList<BookTO> bookStatusRetrieve(BookTO bookTO);

    public ArrayList<Integer> bookDateSearch(MemberTO member);
}
