package com.kbm.instagram.controller;

import com.kbm.instagram.dto.FeedDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.service.FeedService;
import com.kbm.instagram.service.LikeService;
import com.kbm.instagram.service.MemberService;
import com.kbm.instagram.service.S3UploadService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final FeedService feedService;
    private final LikeService likeService;
    private final MemberService memberService;
    private final S3UploadService s3UploadService;

    @PostMapping
    @ApiOperation(value = "좋아요/좋아요 취소하기", notes = "해당 피드에 좋아요를 누릅니다. 이미 좋아요가 되어있는 경우 좋아요를 취소합니다.")
    public List<MemberDto> like (@RequestBody FeedDto feedDto) {
        MemberDto memberDto = memberService.getAuthMember();
        List<MemberDto> likeList = null;
        try {
         likeList = likeService.like(feedDto, memberDto);
        } catch (Exception e) {

        }
        return likeList;
    }

}
