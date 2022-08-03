package com.helper.study.stuhel.board.to;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class BoardTO {
    String title;
    String note;
    int noteSeq;
    String writeDate;
    String topicCd;
    String topicNm;
    int clickAmount;
    @Id
    String writer;
}
