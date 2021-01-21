package com.kbm.instagram.service;

import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestMemberDto;
import com.kbm.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    public final MemberRepository memberRepository;

    @Override
    public MemberDto getMemberInfo(long id) {
        Member member = memberRepository.findById(id).get();
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setEamil(member.getEamil());
        memberDto.setName(member.getEamil());
        memberDto.setProfileUrl(member.getProfileUrl());
        return memberDto;
    }

    @Override
    public MemberDto create(RequestMemberDto signUpMemberDto) {
        Member member = new Member();
        // 프로필 업로드 코드 작성할 것
        member = memberRepository.save(member);
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .email(member.getEamil())
                .name(member.getName())
                .profileUrl(member.getProfileUrl()).build();
        return memberDto;
    }

    @Override
    public MemberDto update(RequestMemberDto signUpMemberDto) {
        Member member = new Member();
        // 프로필 업로드 코드 작성할 것
        member = memberRepository.save(member);
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .email(member.getEamil())
                .name(member.getName())
                .profileUrl(member.getProfileUrl()).build();
        return memberDto;
    }

    @Override
    public void delete(long id) {
        memberRepository.deleteById(id);
    }

}
