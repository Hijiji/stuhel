package com.helper.study.stuhel.member.mapper;

import com.helper.study.stuhel.member.to.MemberTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    String selectIdDoubleCheck(String identity);
    void insertMember(MemberTO memberTO);
    MemberTO selectMemberLogin(MemberTO memberTO);

}
