package com.helper.study.stuhel.service;

import com.helper.study.stuhel.to.MemberTO;
import com.helper.study.stuhel.exception.IdNotFoundException;
import com.helper.study.stuhel.exception.PwMissMatchException;

import java.util.HashMap;

public interface MemberService {

    HashMap<String, Integer> idDoubleCheck(String identity);
    HashMap<String, Integer> memberJoinData(MemberTO memberTO);
    MemberTO login(MemberTO memberTO)throws IdNotFoundException, PwMissMatchException;
}