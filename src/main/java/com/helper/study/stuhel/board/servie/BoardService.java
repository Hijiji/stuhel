package com.helper.study.stuhel.board.servie;

import com.helper.study.stuhel.board.to.BoardCommentTO;
import com.helper.study.stuhel.board.to.BoardTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface BoardService {
    ArrayList<BoardTO> retrieveTopicList();
    HashMap<String, String> saveWrite(BoardTO boardTO);
    ArrayList<BoardTO> retrieveBoardKeyword(HashMap<String, Object> map);
    ArrayList<BoardTO> retrieveBoardList(BoardTO boardTO);
    BoardTO retrieveBoardRead(BoardTO boardTO);
    int addViewCount(BoardTO board);
    ArrayList<BoardCommentTO> retrieveBoardComment(String noteSeq);
    HashMap<String, Integer> saveComment(BoardCommentTO boardComment);
    HashMap<String, Integer> deleteBoardComment(ArrayList<BoardCommentTO> boardComment);
}
