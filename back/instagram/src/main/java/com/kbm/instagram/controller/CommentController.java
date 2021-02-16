package com.kbm.instagram.controller;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.CommentDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.service.CommentService;
import com.kbm.instagram.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final MemberService memberService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "댓글 테스트", notes = "댓글 테스트 중입니다.")
    public String CommnetTest(){
        return "Test";
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value="댓글 입력", notes = "댓글을 입력하고 있습니다.")
    public ResponseEntity<?> InsertCommnet(@RequestBody CommentDto commentDto){
        MemberDto memberDto = memberService.getAuthMember();
        commentDto.setWriter(memberDto);
        commentService.create(commentDto);

        return ResponseEntity.ok().body(commentDto);
    }

//    @PutMapping
//    @ResponseBody
//    @ApiOperation(value="댓글 수정",notes = "댓글을 수정하고 있습니다.")
//    public ResponseEntity<?> updateComment(@RequestBody CommentDto commentDto){
//        MemberDto memberDto = memberService.getAuthMember();
//
//
//        return ResponseEntity.ok().build();
//    }

}
