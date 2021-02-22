package com.kbm.instagram.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCommentDto {

    private Long id;
    private String content;
    private CommentDto commentDto;
    private MemberDto writer;


    @Override
    public String toString() {
        return "SubCommentDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", commentDto=" + commentDto +
                ", writer=" + writer +
                '}';
    }
}
