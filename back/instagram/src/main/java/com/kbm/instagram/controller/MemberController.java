package com.kbm.instagram.controller;

import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.dto.RequestMemberDto;
import com.kbm.instagram.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    @ResponseBody
    public MemberDto getMemberInfo(@PathVariable("id") long id) {
        MemberDto memberDto = null;
        memberDto = memberService.getMemberInfo(id);
        return memberDto;
    }

    @PostMapping
    @ResponseBody
    public MemberDto signUp(@RequestBody RequestMemberDto requestMemberDto) {
        MemberDto memberDto = memberService.create(requestMemberDto);
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
}
