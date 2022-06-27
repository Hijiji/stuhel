package com.helper.study.stuhel.to;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MemberTO {
    @Id
    private String id;
    private String name;
    private int creditNumber;
    private String password;
    private int birthday;
    private String sessionId;

}
