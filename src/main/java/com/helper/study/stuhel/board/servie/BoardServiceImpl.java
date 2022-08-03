package com.helper.study.stuhel.board.servie;

import com.helper.study.stuhel.board.mapper.BoardDAO;
import com.helper.study.stuhel.board.to.BoardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ArrayList<BoardTO> topicRetrieve() {
        return boardDAO.selectTopic();
    }
    @Override
    public void writeSave(BoardTO boardTO) {
        boardDAO.insertNote(boardTO);
    }
    @Override
    public ArrayList<BoardTO> boardKeywordSearch(String fullKeyword) {
        //요거는 서비스단에서 하자..! 앞단에서 받은것을 문자열로 서비스단에 넘겨준 다음, 서비스단에서 가공하여서 디비조사.
        String[] keywordList = fullKeyword.split(" ");
            System.out.println("fullKeyword = " + fullKeyword);
            System.out.println("keywordList = " + keywordList);

        HashMap<String, String[]> map = new HashMap<>();
        map.put("keywordList", keywordList);

        ArrayList<BoardTO> board = boardDAO.selectKeyword(map);
        /*
        String keyWord;
        for(String word:keywordList){
            keyWord= "%"+word+"%";
        }
        */
        return board;
    }
}
