package com.kbm.instagram.controller;

import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestFollowDto;
import com.kbm.instagram.service.FollowService;
import com.kbm.instagram.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final MemberService memberService;

    @PostMapping("/follow")
    @ApiOperation(value = "팔로우하기", notes = "팔로우 하고싶은 사람의 memberId를 받아 팔로우합니다.")
    public ResponseEntity follow(@RequestBody RequestFollowDto requestFollowDto) {
        try {
            MemberDto followingDto = memberService.getMemberInfoByMemberId(requestFollowDto.getMemberId());
            MemberDto followerDto = memberService.getAuthMember();
            followService.follow(followingDto, followerDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unfollow")
    @ApiOperation(value = "언팔로우(팔로우 삭제)하기", notes = "언팔로우 하고싶은 사람의 memberId를 받아 언팔로우합니다.")
    public ResponseEntity<?> unFollow(@RequestBody RequestFollowDto requestFollowDto) {
        try {
            MemberDto followingDto = memberService.getMemberInfoByMemberId(requestFollowDto.getMemberId());
            MemberDto followerDto = memberService.getAuthMember();
            followService.unFollow(followingDto, followerDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/following")
    @ResponseBody
    @ApiOperation(value = "내가 팔로우한 사람 조회 (팔로잉 조회)", notes = "내가 팔로우한 사람을 조회합니다.")
    public List<Member> following() {
        MemberDto memberDto = memberService.getAuthMember();
        List<Member> followings = followService.findFollowing(memberDto);
        return followings;
    }

    @GetMapping("/follower")
    @ResponseBody
    @ApiOperation(value = "나를 팔로우한 사람 조회 (팔로워 조회)", notes = "나를 팔로우한 사람을 조회합니다.")
    public List<Member> follower(@RequestBody RequestFollowDto requestFollowDto) {
        MemberDto memberDto = memberService.getAuthMember();
        List<Member> followers = followService.findFollower(memberDto);
        return followers;
    }

}
