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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String eamil;
    private String password;
    private String profileUrl;

    protected Member() {}

    @Builder
    public Member(Long id, String name, String eamil, String password, String profileUrl) {
        this.id = id;
        this.name = name;
        this.eamil = eamil;
        this.password = password;
        this.profileUrl = profileUrl;
    }
}