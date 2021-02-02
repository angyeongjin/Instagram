package com.kbm.instagram.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberDto {
    private Long id;
    private String memberId;
    private String name;
    private String email;
    private String picture;
}
