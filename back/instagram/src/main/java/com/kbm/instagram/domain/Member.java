package com.kbm.instagram.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String memberId; // 인스타그램의 사용자이름과 같음
    private String name;
    private String eamil;
    private String password;
    private String profileUrl;

}