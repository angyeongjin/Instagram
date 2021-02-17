package com.kbm.instagram.service;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.CommentDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public CommentDto create(CommentDto commentDto) {
        Member member = Member.builder()
                .memberId(commentDto.getWriter().getMemberId())
                .build();
        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .writer(member).build();
        commentRepository.save(comment);
        return commentDto;
    }

    @Override
    @Transactional
    public CommentDto update(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(()->
                new IllegalArgumentException("해당 댓글이 없습니다."));
        comment.update(commentDto.getContent());
        return commentDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 글이 없습니다."));
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto findById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 글이 없습니다."));
        CommentDto commentDto = CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .writer(MemberDto.builder().id(comment.getWriter().getId()).build())
                .build();
        return commentDto;
    }

}