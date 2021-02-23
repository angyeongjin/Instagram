package com.kbm.instagram.service;

import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestMemberDto;

import java.util.List;

public interface MemberService {

    public MemberDto getMemberInfo(long id);
    public MemberDto getMemberInfoByMemberId(String memberId);
    public List<MemberDto> getMemberListByMemberId(String memberId);
    public MemberDto getMemberInfoByEmail(String email);
    public MemberDto save(MemberDto memberDto);
    public MemberDto googleSignUp(MemberDto memberDto);
    public void delete(String memberId);
    public MemberDto getAuthMember();
}
