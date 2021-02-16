package com.kbm.instagram.service;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.CommentDto;
import com.kbm.instagram.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
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

}