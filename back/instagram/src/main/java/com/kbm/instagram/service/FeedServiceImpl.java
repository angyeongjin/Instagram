package com.kbm.instagram.service;

import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.FeedDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestFeedDto;
import com.kbm.instagram.exception.FeedNotFoundException;
import com.kbm.instagram.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;

    @Override
    public FeedDto create(FeedDto feedDto) {
        // todo : 중복 체크
        Feed feed = Feed.builder()
                .contents(feedDto.getContents())
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

        return FeedDto.builder()
                .id(feed.getId())
                .contents(feed.getContents()).build();
    }

    @Override
    public List<FeedDto> findByMemberId(String memberId) {
        List<Feed> feedList = feedRepository.findByMemberId(memberId);
<<<<<<< Updated upstream
        List<FeedDto> feedDtoList= new ArrayList<>();
=======
        List<FeedDto> feedDtoList = new ArrayList<>();
        Optional<Member> memberOptional = memberRepository.findByMemberId(memberId);
        if (memberOptional != null) {
            Member member = memberOptional.get();
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .memberId(member.getMemberId())
                    .email(member.getEmail())
                    .name(member.getName())
                    .picture(member.getPicture()).build();
            for (Feed feed : feedList) {
                System.out.println("=========================================="+feed.getCommentList().size());
                feedDtoList.add(FeedDto.builder()
                        .id(feed.getId())
                        .writer(memberDto)
                        .images(feed.getImages())
                        .contents(feed.getContents()).build());
            }
        }
        return feedDtoList;
    }

    @Override
    public List<FeedDto> findFollowFeedByMemberId(String memberId) {
        List<Member> followers = followRepository.findFollower(memberId);
        Optional<Member> memberOptional = memberRepository.findByMemberId(memberId);
        if (memberOptional != null) followers.add(memberOptional.get());

        List<Feed> feedList = feedRepository.findByMultiMemberId(followers);
        List<FeedDto> feedDtoList = new ArrayList<>();
>>>>>>> Stashed changes
        for (Feed feed : feedList) {
            feedDtoList.add(FeedDto.builder()
                    .id(feed.getId())
                    .contents(feed.getContents()).build());
        }
        return feedDtoList;
    }

    @Override
    public FeedDto update(RequestFeedDto feedDto) {
        Feed updateFeed = feedRepository.findById(feedDto.getId())
                .map(feed -> {
                    feed.setContents(feedDto.getContents());
                    return feedRepository.save(feed);
                })
                .orElseThrow(() -> new FeedNotFoundException(feedDto.getId()));
        return FeedDto.builder()
                .id(updateFeed.getId())
                .contents(updateFeed.getContents()).build();
    }

    @Override
    public void deleteByFeedId(Long id) {
        feedRepository.deleteById(id);
    }
}
