package com.helper.study.stuhel.mypage.mapper;

import com.helper.study.stuhel.book.to.BookTO;
import com.helper.study.stuhel.member.to.MemberTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface MyPageDAO {
    MemberTO retrieve(MemberTO memberTO);
    void changeInfo(MemberTO memberTO);
    ArrayList<BookTO> bookStatusRetrieve(BookTO bookTO);
    void bookCancel(BookTO bookTO);
}
