package com.kbm.instagram.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FeedDto {

    private Long id;
    private String contents;
    private String filter;
    private List<String> images;
    private LocalDateTime createdDate;
    private MemberDto writer;
    private List<MemberDto> likeList;
    private List<CommentDto> commentList;
}
