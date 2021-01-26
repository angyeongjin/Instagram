package com.kbm.instagram.service;

import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestMemberDto;

public interface MemberService {

    public MemberDto getMemberInfo(long id);
    public MemberDto create(RequestMemberDto signUpMemberDto);
    public MemberDto update(RequestMemberDto signUpMemberDto);
    public void delete(long id);
}
