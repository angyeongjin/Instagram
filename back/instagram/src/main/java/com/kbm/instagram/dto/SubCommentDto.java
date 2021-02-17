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

}
