package com.kbm.instagram.service;

import com.kbm.instagram.domain.Follow;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;

    @Override
    public void follow(MemberDto followingDto, MemberDto followerDto) {
        Member following = Member.builder().id(followingDto.getId()).build();
        Member follower = Member.builder().id(followerDto.getId()).build();
        Follow follow = Follow.builder().following(following).follower(follower).build();
        followRepository.save(follow);
    }

    @Override
    public void unFollow(Long followerId, Long followingId) {
        followRepository.deleteFollower(followerId,followingId);
    }

    @Override
    public List<Member> findFollower(MemberDto memberDto) {
        return followRepository.findFollower(memberDto.getMemberId());
    }

    @Override
    public List<Member> findFollowing(MemberDto memberDto) {
        return followRepository.findFollowing(memberDto.getMemberId());
    }
}
