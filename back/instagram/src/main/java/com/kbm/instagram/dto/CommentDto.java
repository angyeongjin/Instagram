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
    private Long id;
    private MemberDto writer;
    private String content;
    private Long feedId;

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", writer=" + writer +
                ", content='" + content + '\'' +
                ", feedId=" + feedId +
                '}';
    }
}

