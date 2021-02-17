package com.kbm.instagram.service;

import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.dto.FeedDto;
import com.kbm.instagram.dto.RequestFeedDto;

import java.util.List;

public interface FeedService {

    FeedDto create(FeedDto feedDto);
    FeedDto findByFeedId(Long id);
    List<FeedDto> findByMemberId(String memberId);
    List<FeedDto> findFollowFeedByMemberId(String memberId);
    FeedDto update(RequestFeedDto feedDto);
    void deleteByFeedId(Long id);
}
