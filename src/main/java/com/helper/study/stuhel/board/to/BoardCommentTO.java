package com.helper.study.stuhel.board.to;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class BoardCommentTO {
    int commentSeq;
    @Id
    int noteSeq;
    String writer;
    String cmt;
    String writeDate;
    int cDepth;
    int cGroup;
}