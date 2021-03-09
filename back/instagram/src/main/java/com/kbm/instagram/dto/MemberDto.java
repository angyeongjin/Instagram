package com.kbm.instagram.dto;

import com.kbm.instagram.domain.Member;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    private List<Member> followerList;
    private List<Member> followingList;
}
