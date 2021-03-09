package com.kbm.instagram.service;

import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.domain.Likes;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.FeedDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;

    @Override
    public List<MemberDto> findLikeList(long feedId) {
        List<Member> likeMemberList = likesRepository.findByFeedId(feedId);
        List<MemberDto> likeMemberDtoList = new ArrayList<>();
        for (Member member : likeMemberList) {
            likeMemberDtoList.add(MemberDto.builder()
                    .id(member.getId())
                    .memberId(member.getMemberId())
                    .email(member.getEmail())
                    .name(member.getName())
                    .picture(member.getPicture()).build());
        }
        return likeMemberDtoList;
    }

    @Override
    @Transactional
    public List<MemberDto> like(FeedDto feedDto, MemberDto memberDto) {
        Optional<Member> member = likesRepository.findOneByMemberId(feedDto.getId(), memberDto.getMemberId());
        if (member.isEmpty()) { // 좋아요
            Likes like = Likes.builder()
                    .member(Member.builder().id(memberDto.getId()).build())
                    .feed(Feed.builder().id(feedDto.getId()).build())
                    .build();
            like = likesRepository.save(like);
        } else { // 좋아요 취소
            System.out.println("취소");
            likesRepository.deleteByFeedIdAndMemberId(feedDto.getId(), memberDto.getId());
        }
        return findLikeList(feedDto.getId());
    }

}
