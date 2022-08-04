package com.helper.study.stuhel.board.servie;

import com.helper.study.stuhel.board.to.BoardTO;

import java.util.ArrayList;

public interface BoardService {
    ArrayList<BoardTO> topicRetrieve();
    void writeSave(BoardTO boardTO);
    ArrayList<BoardTO> boardKeywordSearch(String fullKeyword);
    ArrayList<BoardTO> retrieveBoardList();
    BoardTO boardRead(BoardTO boardTO);
}
