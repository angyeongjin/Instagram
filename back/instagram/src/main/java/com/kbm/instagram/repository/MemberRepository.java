package com.kbm.instagram.repository;

import com.kbm.instagram.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findById(long id);
    public Optional<Member> findByMemberId();
    public Optional<Member> findByEmail(String email);

}
