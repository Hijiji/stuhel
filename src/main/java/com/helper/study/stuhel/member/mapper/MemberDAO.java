package com.helper.study.stuhel.member.mapper;

import com.helper.study.stuhel.member.to.MemberTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
    String selectMemberId(String identity);
    void insertMember(MemberTO memberTO);
    MemberTO selectMemberData(MemberTO memberTO);

}
