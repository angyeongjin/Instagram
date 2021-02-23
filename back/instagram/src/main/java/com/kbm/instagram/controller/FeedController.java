package com.kbm.instagram.controller;

import com.kbm.instagram.dto.FeedDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestFeedDto;
import com.kbm.instagram.service.FeedService;
import com.kbm.instagram.service.LikeService;
import com.kbm.instagram.service.MemberService;
import com.kbm.instagram.service.S3UploadService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {

    private final FeedService feedService;
    private final LikeService likeService;
    private final MemberService memberService;
    private final S3UploadService s3UploadService;

    @PostMapping
    @ApiOperation(value = "새 피드 작성", notes = "피드 정보를 받아 등록합니다.")
    FeedDto newFeed(RequestFeedDto requestFeedDto) {
        FeedDto feedDto = FeedDto.builder()
                .contents(requestFeedDto.getContents())
                .images(new ArrayList<>())
                .writer(memberService.getAuthMember())
                .build();
        System.out.println(requestFeedDto.getContents());
        try {
            if (requestFeedDto.getImages() != null) {
                System.out.println(requestFeedDto.getImages().size());
                for (MultipartFile file : requestFeedDto.getImages()) {
                    feedDto.getImages().add(s3UploadService.uploadFile(file, "feed/"));
                }
            }
            feedDto = feedService.create(feedDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return feedDto;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "피드 조회 (단일)", notes = "피드 id를 받아 피드를 하나 조회합니다.")
    FeedDto getFeedInfo(@PathVariable Long id) {
        List<MemberDto> likeMemberList = likeService.findLikeList(id);
        FeedDto feedDto = feedService.findByFeedId(id);
        feedDto.setLikeList(likeMemberList);
        return feedDto;
    }

    @GetMapping("/member/{memberId}")
    @ApiOperation(value = "특정 회원의 피드 전체 조회", notes = "회원의 memberId를 받아 해당 회원의 피드를 전부 조회합니다.")
    List<FeedDto> getMemberFeed(@PathVariable String memberId) {
        List<FeedDto> feedDtoList = feedService.findByMemberId(memberId);
        for (FeedDto feedDto : feedDtoList) {
            feedDto.setLikeList(likeService.findLikeList(feedDto.getId()));
        }
        return feedDtoList;
    }

    @GetMapping("/main")
    @ApiOperation(value = "팔로우한 사람의 모든 피드 보기 (메인피드)", notes = "내가 팔로우한 사람의 피드를 조회합니다. 나중에 페이징 처리 해야할듯?")
    List<FeedDto> getFollowerFeed() {
        MemberDto memberDto = memberService.getAuthMember();
        List<FeedDto> feedDtoList = feedService.findByMemberId(memberDto.getMemberId());
        for (FeedDto feedDto : feedDtoList) {
            feedDto.setLikeList(likeService.findLikeList(feedDto.getId()));
        }
        return feedDtoList;
    }

    @PutMapping
    @ApiOperation(value = "피드 정보 수정", notes = "피드 정보를 받으 수정합니다.")
    FeedDto updateFeed(@RequestBody RequestFeedDto feedDto) {
        return feedService.update(feedDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "피드 삭제", notes = "파라미터로 피드 id를 받아서 해당 피드를 삭제합니다.")
    void deleteFeed(@PathVariable Long id) {
        feedService.deleteByFeedId(id);
    }
}