package com.kbm.instagram.service;

import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestMemberDto;
import com.kbm.instagram.mapper.MemberMapper;
import com.kbm.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    public final MemberRepository memberRepository;

    @Override
    public MemberDto getMemberInfo(long id) {
        Optional<Member> member = memberRepository.findById(id);
        MemberDto memberDto = null;
        if (member != null) {
            memberDto = MemberMapper.INSTANCE.entityToDto(member.get());
        }
        return memberDto;
    }

    @Override
    public MemberDto getMemberInfoByMemberId(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        MemberDto memberDto = null;
        if (member != null) {
            memberDto = MemberMapper.INSTANCE.entityToDto(member.get());
        }
        return memberDto;
    }

    @Override
    public List<MemberDto> getMemberListByMemberId(String memberId) {
        List<Member> members = memberRepository.findListByMemberId(memberId);
        List<MemberDto> memberDtoList = new ArrayList<>();
        for (Member member : members) {
            memberDtoList.add(MemberMapper.INSTANCE.entityToDto(member));
        }
        return memberDtoList;
    }

    @Override
    public MemberDto getMemberInfoByEmail(String email) throws NoSuchElementException {
        Member member = memberRepository.findByEmail(email).get();
        MemberDto memberDto = MemberMapper.INSTANCE.entityToDto(member);
        return memberDto;
    }

    @Override
    public MemberDto save(MemberDto memberDto) {
        Member member = MemberMapper.INSTANCE.dtoToEntity(memberDto);
        member = memberRepository.save(member);
        memberDto.setId(member.getId());
        return memberDto;
    }

    @Override
    public MemberDto googleSignUp(MemberDto googleMmberDto) {
        googleMmberDto.setMemberId(googleMmberDto.getEmail().split("@")[0]);
        Member member = MemberMapper.INSTANCE.dtoToEntity(googleMmberDto);
        member = memberRepository.save(member);
        return googleMmberDto;
    }

    @Override
    public void delete(String memberId) {
        memberRepository.deleteByMemberId(memberId);
    }

    @Override
    public MemberDto getAuthMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member authMember = (Member) authentication.getPrincipal();
        Member member = memberRepository.findByEmail(authMember.getEmail()).get();
        MemberDto memberDto = MemberMapper.INSTANCE.entityToDto(member);
        return memberDto;
    }

}
