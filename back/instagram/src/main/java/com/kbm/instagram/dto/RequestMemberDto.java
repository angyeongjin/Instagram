package com.kbm.instagram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestMemberDto {
    private String memberId;
    private String name;
    private String email;
    private MultipartFile pictureFile;
}
