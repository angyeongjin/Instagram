package com.kbm.instagram.domain;


import com.kbm.instagram.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //기본키

    @ManyToOne
    private Member writer;

    private String content; //글

    @OneToMany
    private List<SubComment> subComment;

    public void update(String content){
        this.content = content;
    }


}
