package com.kbm.instagram.service;

import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.MemberDto;

import java.util.List;

public interface FollowService {

    public void follow(MemberDto following, MemberDto follower);
    public void unFollow(Long followerId,Long followingId);
    public List<Member> findFollower(MemberDto memberDto);
    public List<Member> findFollowing(MemberDto memberDto);
}
