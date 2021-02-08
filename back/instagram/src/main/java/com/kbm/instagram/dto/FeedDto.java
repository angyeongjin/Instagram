package com.kbm.instagram.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FeedDto {

    private Long id;
    private String contents;
    private List<String> images;
    private MemberDto writer;
}
