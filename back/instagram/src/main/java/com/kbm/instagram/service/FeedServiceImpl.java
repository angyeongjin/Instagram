package com.kbm.instagram.service;

import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.exception.FeedNotFoundException;
import com.kbm.instagram.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;

    @Override
    public Feed create(Feed feed) {
        // todo : 중복 체크
        return feedRepository.save(feed);
    }

    @Override
    public Feed findByFeedId(Long id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new FeedNotFoundException(id));
    }

    @Override
    public Feed update(Feed newFeed) {
        return feedRepository.findById(newFeed.getId())
                .map(feed -> {
                    feed.setContent(newFeed.getContent());
                    return feedRepository.save(feed);
                })
                .orElseThrow(() -> new FeedNotFoundException(newFeed.getId()));
    }

    @Override
    public void deleteByFeedId(Long id) {
        feedRepository.deleteById(id);
    }
}
