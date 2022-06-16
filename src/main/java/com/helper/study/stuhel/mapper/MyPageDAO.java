package com.helper.study.stuhel.mapper;

import com.helper.study.stuhel.to.MemberTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageDAO {
    MemberTO retrieve(MemberTO memberTO);
    MemberTO changeInfo(MemberTO memberTO, String sessionMemberId);
    MemberTO bookInfo(MemberTO MemberTO);
}
