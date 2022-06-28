package com.helper.study.stuhel.service;

import com.helper.study.stuhel.to.MemberTO;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface MyPageService {
    MemberTO retrieve(MemberTO memberTO);
    HashMap<String,Integer> changeInfo(MemberTO memberTO, HttpSession session);
    MemberTO bookInfo(MemberTO MemberTO);

}
