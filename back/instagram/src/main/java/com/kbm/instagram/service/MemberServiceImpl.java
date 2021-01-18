package com.kbm.instagram.service;

import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.repository.MemberRepository;
import com.kbm.instagram.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    public MemberRepository memberRepository;

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
}
