package com.helper.study.stuhel.home.to;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class BookTO {
    @Id
    private int roomId;
    private String bookDate;
    private int bookTime;
    private int bookId;
    private String userId;

}
