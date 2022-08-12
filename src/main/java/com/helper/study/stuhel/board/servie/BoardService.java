package com.helper.study.stuhel.board.servie;

import com.helper.study.stuhel.board.to.BoardTO;

import java.util.ArrayList;

public interface BoardService {
    ArrayList<BoardTO> retrieveTopicList();
    void saveWrite(BoardTO boardTO);
    ArrayList<BoardTO> retrieveBoardKeyword(String fullKeyword);
    ArrayList<BoardTO> retrieveBoardList();
    BoardTO retrieveBoardRead(BoardTO boardTO);
    int addViewCount(BoardTO board);
}
