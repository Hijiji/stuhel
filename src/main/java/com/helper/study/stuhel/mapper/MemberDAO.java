package com.helper.study.stuhel.mapper;

import com.helper.study.stuhel.to.MemberTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
    String selectMemberId(String identity);
    void insertMember(MemberTO memberTO);
    MemberTO selectMemberData(MemberTO memberTO);

}
