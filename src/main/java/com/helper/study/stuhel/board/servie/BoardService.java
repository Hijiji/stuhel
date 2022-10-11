package com.helper.study.stuhel.board.servie;

import com.helper.study.stuhel.board.to.BoardCommentTO;
import com.helper.study.stuhel.board.to.BoardTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface BoardService {
    ArrayList<BoardTO> retrieveTopicList();
    HashMap<String, String> saveWrite(BoardTO board);
    ArrayList<BoardTO> retrieveBoardKeyword(BoardTO board);
    ArrayList<BoardTO> retrieveBoardList(BoardTO board);
    BoardTO retrieveBoardRead(BoardTO board);
    int addViewCount(BoardTO board);
    ArrayList<BoardCommentTO> retrieveBoardComment(BoardCommentTO boardComment);
    HashMap<String, String> saveComment(BoardCommentTO boardComment);
    HashMap<String, String> deleteCheckedBoardComment(ArrayList<BoardCommentTO> boardComment);
    HashMap<String, String> deleteBoard(BoardTO board);
}
