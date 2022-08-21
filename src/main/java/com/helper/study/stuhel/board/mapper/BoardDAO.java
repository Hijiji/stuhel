package com.helper.study.stuhel.board.mapper;

import com.helper.study.stuhel.board.to.BoardCommentTO;
import com.helper.study.stuhel.board.to.BoardTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface BoardDAO {
    ArrayList<BoardTO> selectTopicList();
    void insertWrite(BoardTO board);
    ArrayList<BoardTO> selectBoardKeyword(HashMap<String, Object> map);
    ArrayList<BoardTO> selectBoardList(BoardTO boardTO);
    BoardTO selectReadBoard(BoardTO board);
    int updateViewCount(BoardTO board);
    ArrayList<BoardCommentTO> selectBoardComment(String noteSeq);
    void insertComment(BoardCommentTO boardComment);
    void deleteBoardComment(BoardCommentTO boardComment);
    ArrayList<BoardCommentTO> selectRecomment(BoardCommentTO boardComment);
}
