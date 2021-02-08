package com.kbm.instagram.service;

import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestMemberDto;
import com.kbm.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
            memberDto = MemberDto.builder()
                    .id(member.get().getId())
                    .email(member.get().getEmail())
                    .name(member.get().getName())
                    .picture(member.get().getPicture()).build();
        }
        return memberDto;
    }

    @Override
    public MemberDto getMemberInfoByMemberId(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        MemberDto memberDto = null;
        if (member != null) {
            memberDto = MemberDto.builder()
                    .id(member.get().getId())
                    .email(member.get().getEmail())
                    .name(member.get().getName())
                    .picture(member.get().getPicture()).build();
        }
        return memberDto;
    }

    @Override
    public MemberDto getMemberInfoByEmail(String email) throws NoSuchElementException {
        Member member = memberRepository.findByEmail(email).get();
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .picture(member.getPicture()).build();
        return memberDto;
    }

    @Override
    public MemberDto signUp(RequestMemberDto signUpMemberDto) {
        // 프로필 업로드 코드 작성할 것
        String picture = "";

        Member member = Member.builder()
                .email(signUpMemberDto.getEmail())
                .name(signUpMemberDto.getName())
                .picture(picture).build();

        member = memberRepository.save(member);

        signUpMemberDto.setId(member.getId());
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .picture(member.getPicture()).build();
        return memberDto;
    }

    @Override
    public MemberDto googleSignUp(MemberDto googleMmberDto) {
        String memberId = googleMmberDto.getEmail().split("@")[0];
        Member member = Member.builder()
                .memberId(memberId)
                .email(googleMmberDto.getEmail())
                .name(googleMmberDto.getName())
                .picture(googleMmberDto.getPicture()).build();
        member = memberRepository.save(member);
        return googleMmberDto;
    }

    @Override
    public MemberDto update(RequestMemberDto signUpMemberDto) {
        // 프로필 업로드 코드 작성할 것
        String picture = "";

        Member member = Member.builder()
                .email(signUpMemberDto.getEmail())
                .name(signUpMemberDto.getName())
                .picture(picture).build();

        member = memberRepository.save(member);

        signUpMemberDto.setId(member.getId());
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .picture(member.getPicture()).build();
        return memberDto;
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
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .picture(member.getPicture()).build();
        return memberDto;
    }

}
