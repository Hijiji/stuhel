package com.helper.study.stuhel.board.mapper;

import com.helper.study.stuhel.board.to.BoardTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface BoardDAO {
    ArrayList<BoardTO> selectTopic();
    void insertNote(BoardTO boardTO);
}
