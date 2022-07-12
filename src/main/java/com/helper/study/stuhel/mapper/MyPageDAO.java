package com.helper.study.stuhel.mapper;

import com.helper.study.stuhel.to.BookTO;
import com.helper.study.stuhel.to.MemberTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Mapper
public interface MyPageDAO {
    MemberTO retrieve(MemberTO memberTO);
    void changeInfo(MemberTO memberTO);
    ArrayList<BookTO> bookStatusRetrieve(BookTO bookTO);
    MemberTO bookInfo(MemberTO MemberTO);
    ArrayList<Integer> bookDateSearch(MemberTO member);
}
