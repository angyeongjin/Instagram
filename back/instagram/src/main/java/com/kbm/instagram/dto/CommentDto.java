package com.kbm.instagram.dto;

import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.domain.Member;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentDto {
    private int id;
    private MemberDto writer;
    private String content;

}
