package com.helper.study.stuhel.member.service;

import com.helper.study.stuhel.member.to.MemberTO;
import com.helper.study.stuhel.exception.IdNotFoundException;
import com.helper.study.stuhel.exception.PwMissMatchException;
import com.helper.study.stuhel.member.mapper.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.regex.Pattern;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public MemberTO login(MemberTO memberTO) throws IdNotFoundException, PwMissMatchException { /*로그인*/
        System.out.println("MemberService - login");
        String id = memberDAO.selectIdDoubleCheck(memberTO.getId());
        if (id == null || id.equalsIgnoreCase("null") || id.isEmpty()) {
            throw new IdNotFoundException("존재하지 않는 ID 입니다.");
        } else if (memberTO.getId().equalsIgnoreCase(id)) {
            MemberTO member = memberDAO.selectMemberLogin(memberTO);
            if (member == null || member.getPassword() == null) {
                throw new PwMissMatchException("잘못된 비밀번호입니다.");
            } else {
                return member;
            }
        }
        return null;
    }

    @Override
    public HashMap<String, Integer> idDoubleCheck(String identity) { /*id 중복검사*/
        HashMap<String, Integer> map = new HashMap<>();
        String result;
        String regExp="^[a-z0-9]*$";
        if( !Pattern.matches(regExp, identity) || identity.length()>20 || identity.length()<5){
            map.put("errorCode", -1);
            return map;
        }
        result = memberDAO.selectIdDoubleCheck(identity.toLowerCase());
        if (result != null) {
            map.put("errorCode", -1);
        } else {
            map.put("errorCode", 0);
        }
        return map;
    }

    @Override
    public HashMap<String, Integer> memberJoin(MemberTO memberTO) { /*회원가입*/
        HashMap<String, Integer> map = new HashMap<>();
        memberDAO.insertMember(memberTO);
        map.put("errorCode", 1);
        return map;
    }


}
