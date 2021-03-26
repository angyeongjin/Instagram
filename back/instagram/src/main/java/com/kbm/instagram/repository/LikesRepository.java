package com.kbm.instagram.repository;

import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.domain.Likes;
import com.kbm.instagram.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query("SELECT l.member FROM Likes l WHERE l.feed.id = ?1")
    public List<Member> findByFeedId(long feedId);

    @Query("SELECT l.feed FROM Likes l WHERE l.member.memberId = ?1")
    public List<Feed> findByMemberId(String memberId);

    @Query("SELECT l.member FROM Likes l WHERE l.feed.id = ?1 AND l.member.memberId = ?2")
    public Optional<Member> findOneByMemberId(Long feedId, String memberId);

    public void deleteByFeedIdAndMemberId(Long feedId, long memberId);
}
