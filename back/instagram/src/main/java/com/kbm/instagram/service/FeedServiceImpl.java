package com.kbm.instagram.service;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.CommentDto;
import com.kbm.instagram.dto.FeedDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestFeedDto;
import com.kbm.instagram.exception.FeedNotFoundException;
import com.kbm.instagram.repository.FeedRepository;
import com.kbm.instagram.repository.FollowRepository;
import com.kbm.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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
        for (Feed feed : feedList) {
            List<CommentDto> commentDtoList = new ArrayList<>();
            for (Comment comment : feed.getCommentList() ) {
                commentDtoList.add(CommentToDto(comment));
            }
            feedDtoList.add(FeedDto.builder()
                    .id(feed.getId())
                    .writer(MemberDto.builder()
                            .id(feed.getWriter().getId())
                            .memberId(feed.getWriter().getMemberId())
                            .email(feed.getWriter().getEmail())
                            .name(feed.getWriter().getName())
                            .picture(feed.getWriter().getPicture()).build())
                    .images(feed.getImages())
                    .contents(feed.getContents())
                    .commentList(commentDtoList).build());
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

    // 나중에 dto mapper 따로 관리하는 서비스 만들던가 해야겠음...
    public CommentDto CommentToDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .writer(MemberDto.builder()
                        .id(comment.getWriter().getId())
                        .memberId(comment.getWriter().getMemberId())
                        .email(comment.getWriter().getEmail())
                        .name(comment.getWriter().getName())
                        .picture(comment.getWriter().getPicture()).build())
                .build();
    }
}
