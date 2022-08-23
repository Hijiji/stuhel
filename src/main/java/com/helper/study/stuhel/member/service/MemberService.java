package com.helper.study.stuhel.member.service;

import com.helper.study.stuhel.member.to.MemberTO;
import com.helper.study.stuhel.exception.IdNotFoundException;
import com.helper.study.stuhel.exception.PwMissMatchException;

import java.util.HashMap;

public interface MemberService {

    HashMap<String, String> idDoubleCheck(String identity);
    HashMap<String, String> memberJoin(MemberTO memberTO);
    MemberTO login(MemberTO memberTO)throws IdNotFoundException, PwMissMatchException;
}