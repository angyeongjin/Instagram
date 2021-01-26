package com.kbm.instagram.service;

import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestMemberDto;

public interface MemberService {

    public MemberDto getMemberInfo(long id);
    public MemberDto getMemberInfoByEmail(String email);
    public MemberDto signUp(RequestMemberDto signUpMemberDto);
    public MemberDto googleSignUp(MemberDto memberDto);
    public MemberDto update(RequestMemberDto signUpMemberDto);
    public void delete(long id);
}
