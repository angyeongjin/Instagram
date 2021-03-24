package com.kbm.instagram.service;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.CommentDto;
import com.kbm.instagram.dto.FeedDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestFeedDto;
import com.kbm.instagram.exception.FeedNotFoundException;
import com.kbm.instagram.mapper.CommentMapper;
import com.kbm.instagram.mapper.FeedMapper;
import com.kbm.instagram.mapper.MemberMapper;
import com.kbm.instagram.repository.FeedRepository;
import com.kbm.instagram.repository.FollowRepository;
import com.kbm.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    @Override
    public FeedDto create(FeedDto feedDto) {
        // todo : 중복 체크
        Feed feed = Feed.builder()
                .contents(feedDto.getContents())
                .filter(feedDto.getFilter())
                .images(feedDto.getImages())
                .writer(Member.builder().id(feedDto.getWriter().getId()).build())
                .build();
        feed = feedRepository.save(feed);
        feedDto.setId(feed.getId());
        return feedDto;
    }

    @Override
    public FeedDto findByFeedId(Long id) {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new FeedNotFoundException(id));
        return FeedMapper.INSTANCE.entityToDto(feed);
    }

    @Override
    public List<FeedDto> findByMemberId(String memberId) {
        List<Feed> feedList = feedRepository.findByMemberId(memberId);
        List<FeedDto> feedDtoList = new ArrayList<>();
        Optional<Member> memberOptional = memberRepository.findByMemberId(memberId);
        if (memberOptional != null) {
            for (Feed feed : feedList) {
                feedDtoList.add(FeedMapper.INSTANCE.entityToDto(feed));
            }
        }
        return feedDtoList;
    }

    @Override
    public List<FeedDto> findFollowFeedByMemberId(String memberId) {
        List<Member> followers = followRepository.findFollowing(memberId);
        Optional<Member> memberOptional = memberRepository.findByMemberId(memberId);
        if (memberOptional != null) followers.add(memberOptional.get());
        List<Feed> feedList = feedRepository.findByMultiMemberId(followers);
        List<FeedDto> feedDtoList = new ArrayList<>();
        for (Feed feed : feedList) {
            feedDtoList.add(FeedMapper.INSTANCE.entityToDto(feed));
        }
        return feedDtoList;
    }

    @Override
    public List<FeedDto> findFollowFeedByMemberId(String memberId, Pageable pageable) {
        List<Member> followers = followRepository.findFollowing(memberId);
        Optional<Member> memberOptional = memberRepository.findByMemberId(memberId);
        if (memberOptional != null) followers.add(memberOptional.get());
        List<Feed> feedList = feedRepository.findByMultiMemberId(followers, pageable);
        List<FeedDto> feedDtoList = new ArrayList<>();
        for (Feed feed : feedList) {
            feedDtoList.add(FeedMapper.INSTANCE.entityToDto(feed));
        }
        return feedDtoList;
    }

    @Override
    public FeedDto update(RequestFeedDto feedDto) {
        Feed updateFeed = feedRepository.findById(feedDto.getId())
                .map(feed -> {
                    feed.setContents(feedDto.getContents());
                    feed.setFilter(feedDto.getFilter());
                    return feedRepository.save(feed);
                })
                .orElseThrow(() -> new FeedNotFoundException(feedDto.getId()));
        return FeedDto.builder()
                .id(updateFeed.getId())
                .filter(updateFeed.getFilter())
                .contents(updateFeed.getContents()).build();
    }

    @Override
    public void deleteByFeedId(Long id) {
        feedRepository.deleteById(id);
    }
}
