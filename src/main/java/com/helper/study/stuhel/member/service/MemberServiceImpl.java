package com.helper.study.stuhel.member.service;

import com.helper.study.stuhel.member.to.MemberTO;
import com.helper.study.stuhel.exception.IdNotFoundException;
import com.helper.study.stuhel.exception.PwMissMatchException;
import com.helper.study.stuhel.member.mapper.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }


    /*로그인*/
    @Override
    public MemberTO login(MemberTO memberTO) throws IdNotFoundException, PwMissMatchException {
        System.out.println("MemberService - login");
        String id = memberDAO.selectMemberId(memberTO.getId());
        if (id == null || id.equalsIgnoreCase("null") || id.isEmpty()) {
            throw new IdNotFoundException("존재하지 않는 ID 입니다.");

        } else if (memberTO.getId().equalsIgnoreCase(id)) {
            MemberTO member = memberDAO.selectMemberData(memberTO);

            if (member == null || member.getPassword() == null) {
                throw new PwMissMatchException("잘못된 비밀번호입니다.");

            } else {
                return member;
            }
        }
        return null;
    }

    /*id 중복검사*/
    @Override
    public HashMap<String, Integer> idDoubleCheck(String identity) {
        System.out.println("MemberService - idDoubleCheck");
        HashMap<String, Integer> map = new HashMap<>();
        String result;

        result = memberDAO.selectMemberId(identity);

        /* 동일한 id가 있는지 조사 */
        if (result != null) {
            map.put("errorCode", -1);
        } else {
            map.put("errorCode", 0);
        }

        return map;
    }

    /*회원가입*/
    @Override
    public HashMap<String, Integer> memberJoinData(MemberTO memberTO) {
        System.out.println("MemberService - memberJoinData");
        HashMap<String, Integer> map = new HashMap<>();

        memberDAO.insertMember(memberTO);
        map.put("errorCode", 1);
        return map;
    }


}
