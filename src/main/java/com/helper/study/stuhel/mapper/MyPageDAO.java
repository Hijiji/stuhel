package com.helper.study.stuhel.mapper;

import com.helper.study.stuhel.to.MemberTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Mapper
public interface MyPageDAO {
    MemberTO retrieve(MemberTO memberTO);
    void changeInfo(MemberTO memberTO);
    MemberTO bookInfo(MemberTO MemberTO);
    ArrayList<Integer> bookDateSearch(MemberTO member);
}
