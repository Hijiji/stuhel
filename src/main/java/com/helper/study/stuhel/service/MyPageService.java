package com.helper.study.stuhel.service;

import com.helper.study.stuhel.to.MemberTO;

import java.util.HashMap;

public interface MyPageService {
    MemberTO retrieve(MemberTO memberTO);
    MemberTO bookInfo(MemberTO MemberTO);
}
