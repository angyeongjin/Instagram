package com.kbm.instagram.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.kbm.instagram.dto.GoogleTokenDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestMemberDto;
import com.kbm.instagram.service.MemberService;
import com.kbm.instagram.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ValidationService validationService;

    @GetMapping
    @ResponseBody
    public MemberDto getMemberInfo() {
        MemberDto memberDto = memberService.getAuthMember();
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

    @PostMapping("/google")
    @ResponseBody
    public ResponseEntity<?> getMemberByIdToken(@RequestBody GoogleTokenDto googleTokenDto) {
        MemberDto memberDto = null;
        MemberDto googleMemberDto = null;
        System.out.println(googleTokenDto.getIdToken());
        try {
            googleMemberDto = validationService.validationByIdToken(googleTokenDto.getIdToken());
            memberDto = memberService.getMemberInfoByEmail(googleMemberDto.getEmail());
        } catch (GeneralSecurityException e) {
            System.out.println("!23213");
        } catch (IOException e) {
            System.out.println("1ee123");
        } catch (NoSuchElementException e) {
            System.out.println("Not registered as a member.. signUp member");
            memberService.googleSignUp(googleMemberDto);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            System.out.println("token is not vailed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }
}
