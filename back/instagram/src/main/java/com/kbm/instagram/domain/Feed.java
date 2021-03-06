package com.kbm.instagram.domain;

import lombok.*;

import com.kbm.instagram.domain.Member;

import javax.persistence.*;
import java.util.List;

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
    private String contents;

    @Setter
    private String filter;

    @ElementCollection
    private List<String> images;

    @ManyToOne
    private Member writer;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<Comment> commentList;

    // todo
    // Member
    // 이미지 List
    // 댓글 list
    // 좋아요 list

}
