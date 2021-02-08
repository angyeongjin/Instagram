package com.kbm.instagram.repository;

import com.kbm.instagram.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    @Query("SELECT f FROM Feed f WHERE f.writer.memberId = ?1")
    public List<Feed> findByMemberId(String memberId);
}