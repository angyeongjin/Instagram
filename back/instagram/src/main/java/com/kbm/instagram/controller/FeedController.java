package com.kbm.instagram.controller;

import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {

    private final FeedService feedService;

    @PostMapping
    Feed newFeed(@RequestBody Feed feed) {
        return feedService.create(feed);
    }

    @GetMapping("/{id}")
    Feed one(@PathVariable Long id) {
        return feedService.findByFeedId(id);
    }

    @PutMapping
    Feed updateFeed(@RequestBody Feed newFeed) {
        return feedService.update(newFeed);
    }

    @DeleteMapping("/{id}")
    void deleteFeed(@PathVariable Long id) {
        feedService.deleteByFeedId(id);
    }
}
