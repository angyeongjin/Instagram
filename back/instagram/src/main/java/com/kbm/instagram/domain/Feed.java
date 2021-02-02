package com.kbm.instagram.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String content;
    private LocalDateTime createdTime;

    // todo
    // Member
    // 이미지 List
    // 댓글 list
    // 좋아요 list

    public Feed() {
        this.createdTime = LocalDateTime.now();
    }

    public Feed(String content) {
        this.content = content;
        this.createdTime = LocalDateTime.now();
    }
}
