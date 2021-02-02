package com.kbm.instagram.controller;

import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping("/feed")
    Feed newFeed(@RequestBody Feed feed) {
        return feedService.create(feed);
    }

    @GetMapping("/feed/{id}")
    Feed one(@PathVariable Long id) {
        return feedService.findByFeedId(id);
    }

    @PutMapping("/feed/{id}")
    Feed updateFeed(@RequestBody Feed newFeed, @PathVariable Long id) {
        return feedService.update(newFeed, id);
    }

    @DeleteMapping("feed/{id}")
    void deleteFeed(@PathVariable Long id) {
        feedService.deleteByFeedId(id);
    }
}
