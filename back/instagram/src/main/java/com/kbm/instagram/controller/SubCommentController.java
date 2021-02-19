package com.kbm.instagram.controller;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.dto.CommentDto;
import com.kbm.instagram.dto.SubCommentDto;
import com.kbm.instagram.service.CommentService;
import com.kbm.instagram.service.SubCommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subComment")
public class SubCommentController {

    private final SubCommentService subCommentService;
    private final CommentService commentService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "대댓글 불러오기" ,notes="대댓글을 불러오고 있습니다.")
    public String SubCommentTest(){
        return "subCommentTest";
    }

    @PostMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "대댓글 입력" , notes = "대댓글을 입력하고 있습니다.")
    public SubCommentDto insertSubComment(@PathVariable Long id, @RequestBody SubCommentDto subCommentDto){
        CommentDto commentDto = commentService.findById(id);
        subCommentDto.setCommentDto(commentDto);
        subCommentService.create(subCommentDto);

        return subCommentDto;
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "대댓글 수정" , notes = "대댓글을 수정하고 있습니다.")
    public SubCommentDto updateSubComment(@RequestBody SubCommentDto subCommentDto){
        subCommentService.update(subCommentDto);
        return subCommentDto;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "대댓글 삭제", notes = "대댓글을 삭제하고 있습니다.")
    public ResponseEntity<?> deleteSubComment(@PathVariable Long id){
        subCommentService.delete(id);
        return ResponseEntity.ok().build();
    }



}
