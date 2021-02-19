package com.kbm.instagram.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RequestFeedDto {

    private Long id;
    private String contents;
    private List<MultipartFile> images;
}
