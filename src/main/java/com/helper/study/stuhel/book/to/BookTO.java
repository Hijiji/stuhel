package com.helper.study.stuhel.book.to;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Data
@Entity
public class BookTO {
    @Id
    private int roomId;
    private String bookingDate;
    private int bookingTime;
    private int bookingId;
    private String userId;

}
