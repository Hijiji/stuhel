package com.helper.study.stuhel.board.mapper;

import com.helper.study.stuhel.board.to.BoardCommentTO;
import com.helper.study.stuhel.board.to.BoardTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface BoardMapper {
    ArrayList<BoardTO> selectTopicList();
    void insertWrite(BoardTO board);
    ArrayList<BoardTO> selectBoardKeyword(BoardTO board);
    ArrayList<BoardTO> selectBoardList(BoardTO boardTO);
    BoardTO selectReadBoard(BoardTO board);
    int updateViewCount(BoardTO board);
    ArrayList<BoardCommentTO> selectBoardComment(BoardCommentTO boardComment);
    void insertComment(BoardCommentTO boardComment);
    void deleteBoardComment(BoardCommentTO boardComment);
    ArrayList<BoardCommentTO> selectRecomment(BoardCommentTO boardComment);
}
