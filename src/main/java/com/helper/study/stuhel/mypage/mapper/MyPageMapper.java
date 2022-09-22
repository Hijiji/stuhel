package com.helper.study.stuhel.mypage.mapper;

import com.helper.study.stuhel.home.to.BookTO;
import com.helper.study.stuhel.member.to.MemberTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface MyPageMapper {
    MemberTO selectMemberInfo(MemberTO memberTO);
    void deleteMemberInfo(MemberTO memberTO);
    void updateMemberInfo(MemberTO memberTO);
    ArrayList<BookTO> selectBookStatus(BookTO bookTO);
    void deleteBook(BookTO bookTO);
}
