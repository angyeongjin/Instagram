package com.kbm.instagram.service;

import com.kbm.instagram.domain.Feed;

import java.util.List;
import java.util.Optional;

public interface FeedService {

    Feed create(Feed feed);
    Feed findByFeedId(Long id);
    Feed update(Feed newFeed, Long id);
    void deleteByFeedId(Long id);
}
