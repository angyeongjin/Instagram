package com.kbm.instagram.domain;

import lombok.*;

import com.kbm.instagram.domain.Member;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feed extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String content;

//    @ManyToOne
//    private Member writer;

    // todo
    // Member
    // 이미지 List
    // 댓글 list
    // 좋아요 list

}
