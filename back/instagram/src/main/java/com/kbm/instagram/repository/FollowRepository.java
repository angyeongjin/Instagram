package com.kbm.instagram.repository;

import com.kbm.instagram.domain.Follow;
import com.kbm.instagram.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT f.follower FROM Follow f WHERE f.following.memberId = ?1")
    public List<Member> findFollower(String memberId);

    @Query("SELECT f.following FROM Follow f WHERE f.follower.memberId = ?1")
    public List<Member> findFollowing(String memberId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Follow f WHERE f.follower.id = ?1 AND f.following.id = ?2")
    public void deleteFollower(long followerId, long followingId);
}
