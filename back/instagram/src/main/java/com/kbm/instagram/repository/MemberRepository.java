package com.kbm.instagram.repository;

import com.kbm.instagram.domain.Follow;
import com.kbm.instagram.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findById(long id);
    public Optional<Member> findByMemberId(String memberId);
    public Optional<Member> findByEmail(String email);
    @Query("SELECT m FROM Member m WHERE m.memberId LIKE CONCAT(?1,'%')")
    public List<Member> findListByMemberId(String MemberId);
    public void deleteByMemberId(String memberId);
}
