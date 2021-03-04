package com.kbm.instagram.dto;

import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.domain.SubComment;
import lombok.*;

import java.util.List;

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
    private List<SubCommentDto> subCommentList;

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

