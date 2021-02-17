package com.kbm.instagram.controller;

import com.kbm.instagram.dto.GoogleTokenDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestMemberDto;
import com.kbm.instagram.service.MemberService;
import com.kbm.instagram.service.ValidationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
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
    @ApiOperation(value = "로그인된 회원 정보 조회", notes = "현재 로그인 되어 있는 회원의 정보를 조회합니다.")
    public MemberDto getMemberInfo() {
        MemberDto memberDto = memberService.getAuthMember();
        return memberDto;
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정합니다.")
    public MemberDto updateMember(@RequestBody RequestMemberDto requestMemberDto) {
        MemberDto memberDto = memberService.update(requestMemberDto);
        return memberDto;
    }

    @GetMapping("/search/{memberId}")
    @ResponseBody
    @ApiOperation(value = "회원 아이디로 회원 검색", notes = "앞글자부터 일치하는 회원 아이디로 회원 정보를 검색합니다")
    public List<MemberDto> searchMemberList(@PathVariable String memberId) {
        List<MemberDto> memberDtoList = memberService.getMemberListByMemberId(memberId);
        return memberDtoList;
    }

    @DeleteMapping("/{memberId}")
    @ResponseBody
    @ApiOperation(value = "회원 정보 삭제", notes = "memberId 를 파라미터로 받아 회원 정보를 삭제합니다.")
    public ResponseEntity<?> deleteMember(String memberId) {
        memberService.delete(memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/google")
    @ResponseBody
    @ApiOperation(value = "구글 아이디로 로그인", notes = "구글 로그인 후 받은 idToken 으로 로그인합니다. 회원 정보가 없으면 자동 회원가입 후 로그인 됩니다.")
    public ResponseEntity<?> getMemberByIdToken(@RequestBody GoogleTokenDto googleTokenDto) {
        MemberDto memberDto = null;
        MemberDto googleMemberDto = null;
        System.out.println(googleTokenDto.getIdToken());
        try {
            googleMemberDto = validationService.validationByIdToken(googleTokenDto.getIdToken());
            memberDto = memberService.getMemberInfoByEmail(googleMemberDto.getEmail());
        } catch (GeneralSecurityException e) {
            System.out.println("token is not vailed");
        } catch (IOException e) {
            System.out.println("input value is not vailed");
        } catch (NoSuchElementException e) {
            System.out.println("Not registered as a member.. signUp member. email : " + googleMemberDto.getEmail());
            memberService.googleSignUp(googleMemberDto);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            System.out.println("token is not vailed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }
}
