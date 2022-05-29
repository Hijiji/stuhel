package com.helper.study.stuhel.to;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class BookTO {

    private int bookingId;
    private String userId;
    @Id
    private int roomId;
    private String bookingDate;
    private int bookingTime;
}
