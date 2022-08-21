package com.helper.study.stuhel.board.servie;

import com.helper.study.stuhel.board.mapper.BoardDAO;
import com.helper.study.stuhel.board.to.BoardCommentTO;
import com.helper.study.stuhel.board.to.BoardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private final BoardDAO boardDAO;
    BoardServiceImpl(BoardDAO boardDAO){
        this.boardDAO=boardDAO;
    }
    @Override
    public ArrayList<BoardTO> retrieveTopicList() {
        return boardDAO.selectTopicList();
    }
    @Override
    public void saveWrite(BoardTO boardTO) {
        boardDAO.insertWrite(boardTO);
    }
    @Override
    public ArrayList<BoardTO> retrieveBoardKeyword(HashMap<String, Object> map) {
        //요거는 서비스단에서 하자..! 앞단에서 받은것을 문자열로 서비스단에 넘겨준 다음, 서비스단에서 가공하여서 디비조사.
        String[] keywordList = ((String)map.get("fullKeyword")).split(" ");

        //HashMap<String, String[]> map = new HashMap<>();
        map.put("keywordList", keywordList);

        ArrayList<BoardTO> board = boardDAO.selectBoardKeyword(map);
        return board;
    }

    @Override
    public ArrayList<BoardTO> retrieveBoardList(BoardTO boardTO) {
        ArrayList<BoardTO>board=boardDAO.selectBoardList(boardTO);
        return board;
    }

    @Override
    public BoardTO retrieveBoardRead(BoardTO boardTO) {
        BoardTO board=boardDAO.selectReadBoard(boardTO);
        board.setNote(board.getNote().replaceAll("\n\r","</br>"));
        System.out.println("board = " + board);
        return board;
    }

    @Override
    public int addViewCount(BoardTO board) {
        boardDAO.updateViewCount(board);
        return 0;
    }

    @Override
    public ArrayList<BoardCommentTO> retrieveBoardComment(String noteSeq) {
        return boardDAO.selectBoardComment(noteSeq);
    }

    @Override
    public HashMap<String, Integer> saveComment(BoardCommentTO boardComment) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("errorCd",0);
        try {
            boardDAO.insertComment(boardComment);
        }catch (Exception e){
            e.printStackTrace();
            map.put("errorCd",-1);
        }
        return map;
    }

    @Override
    public HashMap<String, Integer> deleteBoardComment(ArrayList<BoardCommentTO> boardComment) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("errorCd", 0);
        ArrayList<BoardCommentTO> deleteBoardList = new ArrayList<>();
        try {
            for(BoardCommentTO board:boardComment){
                deleteBoardList = boardDAO.selectRecomment(board); //원댓글 삭제시 대댓글삭제
            }
            if(!deleteBoardList.isEmpty()){
                for(BoardCommentTO deleteBoardComment:deleteBoardList){
                    boardDAO.deleteBoardComment(deleteBoardComment);
                }
            }
            for(BoardCommentTO a:boardComment){
                boardDAO.deleteBoardComment(a);
            }

        }catch(Exception e){
            e.printStackTrace();
            map.put("errorCd", -1);
        }
        return map;
    }
}
