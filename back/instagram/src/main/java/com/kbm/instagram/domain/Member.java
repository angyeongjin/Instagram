package com.kbm.instagram.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // string auto 되는지 찾아보기
    private long id;
    private String memberId;
    private String name;
    private String eamil;
    private String password;
    private String profileUrl;

    protected Member() {}

    @Builder
    public Member(long id, String memberId, String name, String eamil, String password, String profileUrl) {
        this.id = id;
        this.memberId = memberId;
        this.name = name;
        this.eamil = eamil;
        this.password = password;
        this.profileUrl = profileUrl;
    }
}