package com.kbm.instagram.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.kbm.instagram.dto.GoogleTokenDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestMemberDto;
import com.kbm.instagram.service.MemberService;
import com.kbm.instagram.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ValidationService validationService;

    @GetMapping("/{id}")
    @ResponseBody
    public MemberDto getMemberInfo(@PathVariable("id") long id) {
        MemberDto memberDto = null;
        memberDto = memberService.getMemberInfo(id);
        return memberDto;
    }

    @PutMapping
    @ResponseBody
    @ResponseStatus()
    public MemberDto updateMember(@RequestBody RequestMemberDto requestMemberDto) {
        MemberDto memberDto = memberService.update(requestMemberDto);
        return memberDto;
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<?> deleteMember(long id) {
        memberService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @ResponseBody
    public MemberDto getMemberByIdToken(@RequestBody GoogleTokenDto googleTokenDto) {
        MemberDto memberDto = null;
        try {
            MemberDto googleMemberDto = validationService.validationByIdToken(googleTokenDto.getIdTokenString());
            memberDto = memberService.getMemberInfoByEmail(googleMemberDto.getEmail());
            if (memberDto == null) {
                memberService.googleSignUp(googleMemberDto);
            }
            // 로그인
        } catch (GeneralSecurityException e) {

        } catch (IOException e) {

        }
        return memberDto;
    }
}
