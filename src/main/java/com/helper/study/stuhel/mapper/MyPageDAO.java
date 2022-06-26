package com.helper.study.stuhel.mapper;

import com.helper.study.stuhel.to.MemberTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyPageDAO {
    MemberTO retrieve(MemberTO memberTO);
    MemberTO changeInfo(@Param("id")String id, @Param("name") String name, @Param("password") String password, @Param("birthDay") int birthDay);
    MemberTO bookInfo(MemberTO MemberTO);
}
