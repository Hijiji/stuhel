package com.helper.study.stuhel.board.servie;

import com.helper.study.stuhel.board.mapper.BoardMapper;
import com.helper.study.stuhel.board.to.BoardCommentTO;
import com.helper.study.stuhel.board.to.BoardTO;
import com.helper.study.stuhel.member.to.MemberTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private final BoardMapper boardMapper;
    BoardServiceImpl(BoardMapper boardMapper){
        this.boardMapper = boardMapper;
    }
    @Override
    public ArrayList<BoardTO> retrieveTopicList() {
        return boardMapper.selectTopicList();
    }
    @Override
    @Transactional
    public HashMap<String, String> saveWrite(BoardTO board) {
        HashMap<String, String> map = new HashMap<>();
        if(board.getTitle().isEmpty()||board.getNote().isEmpty()){
            map.put("errorCd","Y");
            map.put("errorMsg","제목 / 내용을 입력해주세요");
            return map;
        }
        System.out.println("저장1board = " + board);
        board.setNote(board.getNote().replaceAll("\n\r","<br/>")); //엔터키 유효하게 변경
        System.out.println("저장2board = " + board);
        map.put("errorCd", "N");
        map.put("errorMsg", "성공적으로 저장되었습니다.");
        try {
            boardMapper.insertWrite(board);
        }catch (Exception e){
            e.printStackTrace();
            map.put("errorCd", "Y");
            map.put("errorMsg", "저장중 오류발생");
        }
        return map;
    }
    @Override
    public ArrayList<BoardTO> retrieveBoardKeyword(BoardTO board) {
        //문장형식으로 들어온 keyword 를 잘라서 mapper에 전달
        board.setKeywordList(board.getFullKeyword().split(" "));
        return boardMapper.selectBoardKeyword(board);
    }

    @Override
    public ArrayList<BoardTO> retrieveBoardList(BoardTO board) {
        return boardMapper.selectBoardList(board);
    }

    @Override
    public BoardTO retrieveBoardRead(BoardTO boardTO) {
        BoardTO board= boardMapper.selectReadBoard(boardTO);
        board.setNote(board.getNote().replaceAll("<br/>","\n")); //유효 엔터키로 변경
        System.out.println("board = " + board);
        return board;
    }

    @Override
    @Transactional
    public int addViewCount(BoardTO board) {
        int returnInfo;
        if(board.getWriter().equals(board.getReader())){
            return -1;
        }
        try {
            boardMapper.updateViewCount(board);
            returnInfo=0;
        }catch (Exception e){
            e.printStackTrace();
            returnInfo=-1;
        }
        return returnInfo;
    }

    @Override
    public ArrayList<BoardCommentTO> retrieveBoardComment(BoardCommentTO boardComment) {
        return boardMapper.selectBoardComment(boardComment);
    }

    @Override
    @Transactional
    public HashMap<String, String> saveComment(BoardCommentTO boardComment) {
        HashMap<String, String> map = new HashMap<>();
        map.put("errorCd","N");
        if(boardComment.getCmt().isEmpty()){
            map.put("errorCd","Y");
            map.put("errorMsg","댓글이 입력되지 않았습니다.");
            return map;
        }
        try {
            boardMapper.insertComment(boardComment);
        }catch (Exception e){
            e.printStackTrace();
            map.put("errorCd","Y");
            map.put("errorMsg","댓글 저장 중 오류발생");
        }
        return map;
    }

    @Override
    @Transactional
    public HashMap<String, String> deleteCheckedBoardComment(ArrayList<BoardCommentTO> boardComment) {
        HashMap<String, String> map = new HashMap<>();
        map.put("errorCd", "N");map.put("errorMsg", "삭제완료");
        ArrayList<BoardCommentTO> deleteBoardList = new ArrayList<>();
        try {
            for(BoardCommentTO board:boardComment){
                deleteBoardList = boardMapper.selectRecomment(board); //원댓글에 달린 대댓글 조회
            }
            if(!deleteBoardList.isEmpty()){
                for(BoardCommentTO deleteBoardComment:deleteBoardList){
                    boardMapper.deleteCheckedBoardComment(deleteBoardComment); //원댓글에 달린 대댓글 전부 삭제
                }
            }
            for(BoardCommentTO a:boardComment){ //원댓글 삭제
                boardMapper.deleteCheckedBoardComment(a);
            }

        }catch(Exception e){
            e.printStackTrace();
            map.put("errorCd", "Y");
            map.put("errorMsg", "댓글 삭제중 오류발생");
        }
        return map;
    }

    @Override
    @Transactional
    public HashMap<String, String> deleteBoard(BoardTO board) {
        HashMap<String, String> map = new HashMap<>();
        map.put("errorCd","N");
        map.put("errorMsg","게시글이 삭제되었습니다.");
        try {
            boardMapper.deleteBoard(board);
            boardMapper.deleteBoardComment(board); //삭제된 게시글의 댓글 삭제
        }catch (Exception e){
            e.printStackTrace();
            map.put("errorCd","Y");
            map.put("errorMsg","오류발생");
        }
        return map;
    }
}
