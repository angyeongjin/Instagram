package com.kbm.instagram.service;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.CommentDto;
import com.kbm.instagram.dto.FeedDto;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.mapper.CommentMapper;
import com.kbm.instagram.repository.CommentRepository;
import com.kbm.instagram.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final FeedRepository feedRepository;

    @Override
    @Transactional
    public CommentDto create(CommentDto commentDto) {
        Feed feed = feedRepository.findById(commentDto.getFeedId()).orElseThrow(()->
                new IllegalArgumentException("해당 피드가 없습니다."));

        Member member = Member.builder()
                .id(commentDto.getWriter().getId())
                .build();
        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .writer(member)
                .feed(feed)
                .build();

        commentRepository.save(comment);
        commentDto.setId(comment.getId());
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
        CommentDto commentDto = CommentMapper.INSTANCE.entityToDto(comment);
        return commentDto;
    }

}