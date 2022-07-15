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

    ArrayList<BookTO> bookStatusRetrieve(BookTO bookTO);

    HashMap<String,String> bookCancel(BookTO bookTO);

}
