package com.helper.study.stuhel.mypage.service;

import com.helper.study.stuhel.common.controller.SessionController;
import com.helper.study.stuhel.mypage.mapper.MyPageMapper;
import com.helper.study.stuhel.home.to.BookTO;
import com.helper.study.stuhel.member.to.MemberTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MyPageServiceImpl implements MyPageService{
    private final MyPageMapper myPageMapper;
    @Autowired
    public MyPageServiceImpl(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }
    @Override
    public MemberTO retrieveMemberInfo(MemberTO memberTO) {
        return myPageMapper.selectMemberInfo(memberTO);
    }
    @Override
    @Transactional
    public HashMap<String, String> deleteMemberInfo(MemberTO memberTO) {
        HashMap<String, String> map = new HashMap<>();
        map.put("errorCd","N");
        try{
            myPageMapper.deleteMemberInfo(memberTO);  //탈퇴요청회원 정보삭제
            myPageMapper.deleteMemberBoardCommentData(memberTO);  //탈퇴요청회원 댓글삭제
            myPageMapper.deleteMemberBoardData(memberTO);   //탈퇴요청회원 게시글삭제
            myPageMapper.deleteMemberRoomBookData(memberTO);    //탈퇴요청회원 예약현황삭제
        }catch(Exception e){
            e.printStackTrace();
            map.put("errorCd","Y");
            map.put("errorMsg","탈퇴중 오류가 발생했습니다. 잠시후 다시 시도해주세요.");
        }
        return map;
    }
    @Override
    @Transactional
    public HashMap<String,String> changeMemberInfo(MemberTO memberTO,HttpSession session){
        HashMap<String, String> map = new HashMap<>();
        map.put("errorCd", "N");
        map.put("errorMsg", "성공");
        //변경저장요청은 들어왔지만, 변경사항된 회원정보가 없을경우
        if(memberTO.getId().isEmpty()&&memberTO.getBirth()==0&&memberTO.getPassword().isEmpty()&&memberTO.getName().isEmpty()) {
            map.put("errorCd", "Y");
            map.put("errorMsg", "변경할 데이터가 없습니다.");
            return map;
        }
        try {
            //회원 정보변경
            myPageMapper.updateMemberInfo(memberTO);
            //회원ID가 변경되지 않은 경우 session에 있는 회원 ID를 가져온다.
            if(memberTO.getId().isEmpty() || memberTO.getId()==""){
                memberTO.setId((String) session.getAttribute("memberId"));
            }else{//회원ID가 변경된 경우 회원이 활동 내역 찾아서 ID 변경
                myPageMapper.updateBoardInfo(memberTO);
                myPageMapper.updateBoardCmtInfo(memberTO);
                myPageMapper.updateRoomBookInfo(memberTO);
            }
            //memberTO.setId(memberTO.getId());
            //회원 ID를담은 TO를 회원정보조회 메서드로 보내 변경된 회원정보를 가져온다.
            memberTO=retrieveMemberInfo(memberTO);
            //변경된 회원정보를 session에 담아준다.
            SessionController.loginSession(session, memberTO);
        }catch (Exception e) {
            map.put("errorCd", "Y");
            map.put("errorMsg", "정보변경 중 데이터 에러발생");
            e.printStackTrace();
        }
        return map;
    }
    @Override
    public ArrayList<BookTO> retrieveBookStatus(BookTO bookTO) {
        System.out.println("bookTO.getBookingDate() = " + bookTO.getBookDate());
        System.out.println("bookTO.getUserId() = " + bookTO.getUserId());
        ArrayList<BookTO> book= myPageMapper.selectBookStatus(bookTO);
        return book;
    }
    @Override
    @Transactional
    public HashMap<String,String> cancelBook(ArrayList<BookTO> bookTOList){
        HashMap<String, String> map = new HashMap<>();
        try{
            for(BookTO bookTO:bookTOList) {
                myPageMapper.deleteBook(bookTO);
            }
            map.put("errorCode","Y");
        }catch (Exception e) {
            map.put("errorCode", "N");
            e.printStackTrace();
        }
        return map;
    }
}
