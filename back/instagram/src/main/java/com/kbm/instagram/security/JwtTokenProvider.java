package com.kbm.instagram.security;

import com.kbm.instagram.domain.Member;
import com.kbm.instagram.repository.MemberRepository;
import com.kbm.instagram.service.MemberService;
import com.kbm.instagram.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
class JwtTokenProvider{

    private final MemberRepository memberRepository;
    private final ValidationService validationService;
    private final MemberService memberService;

    //토큰 인증 정보 조회
    public Authentication getAuthentication(String token) throws IOException, GeneralSecurityException, NullPointerException {
        Optional<Member> member = memberRepository.findByEmail(getUserPk(token));
        UserDetails userDetails = (UserDetails) member.get();
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) throws IOException, GeneralSecurityException {
        //return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
        String userPk = validationService.validationByIdToken(token).getEmail();
        return userPk;
    }

    //request의 Header에서 token 파싱
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("id_token");
    }

}