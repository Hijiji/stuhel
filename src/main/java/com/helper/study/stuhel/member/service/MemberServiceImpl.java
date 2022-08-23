package com.helper.study.stuhel.member.service;

import com.helper.study.stuhel.member.to.MemberTO;
import com.helper.study.stuhel.exception.IdNotFoundException;
import com.helper.study.stuhel.exception.PwMissMatchException;
import com.helper.study.stuhel.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.regex.Pattern;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public MemberTO login(MemberTO memberTO) throws IdNotFoundException, PwMissMatchException {

        String id = memberMapper.selectIdDoubleCheck(memberTO.getId());
        if (id == null || id.equalsIgnoreCase("null") || id.isEmpty()) {
            throw new IdNotFoundException("존재하지 않는 ID 입니다.");
        } else if (memberTO.getId().equalsIgnoreCase(id)) {
            MemberTO member = memberMapper.selectMemberLogin(memberTO);
            if (member == null || member.getPassword() == null) {
                throw new PwMissMatchException("잘못된 비밀번호입니다.");
            } else {
                return member;
            }
        }
        return null;
    }

    @Override
    public HashMap<String, String> idDoubleCheck(String identity) {
        HashMap<String, String> map = new HashMap<>();
        String result;
        String regExp="^[a-z0-9]*$";
        if( !Pattern.matches(regExp, identity) || identity.length()>20 || identity.length()<5){
            map.put("errorCode", "Y");
            return map;
        }
        result = memberMapper.selectIdDoubleCheck(identity.toLowerCase());
        if (result != null) {
            map.put("errorCode", "Y");
        } else {
            map.put("errorCode", "N");
        }
        return map;
    }

    @Override
    public HashMap<String, String> memberJoin(MemberTO memberTO) {
        HashMap<String, String> map = new HashMap<>();

        if(idDoubleCheck(memberTO.getId()).get("errorCode")=="Y"){
            map.put("errorCode", "Y");
            map.put("errorMsg", "ID가 중복되었습니다.");
            return map;
        }else if(!Integer.toString(memberTO.getBirth()).matches("[0-9]+")){
            map.put("errorCode", "Y");
            map.put("errorMsg", "생년월일을 숫자로만 입력해주세요.");
            return map;
        }
        memberMapper.insertMember(memberTO);
        map.put("errorCode", "N");
        return map;
    }


}
