package com.kbm.instagram.service;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.domain.SubComment;
import com.kbm.instagram.dto.SubCommentDto;
import com.kbm.instagram.repository.SubCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubCommentServiceImpl implements SubCommentService{

    private final SubCommentRepository subCommentRepository;


    @Override
    @Transactional
    public SubCommentDto create(SubCommentDto subCommentDto) {
        //comment 생성
        Comment comment = Comment.builder()
                .id(subCommentDto.getCommentDto().getId())
                .content(subCommentDto.getCommentDto().getContent())
                .build();
        //member 생성
        Member member = Member.builder()
                .id(subCommentDto.getWriter().getId())
                .build();
        //subComment 생성
        SubComment subComment = SubComment.builder()
                .id(subCommentDto.getId())
                .comment(comment)
                .content(subCommentDto.getContent())
                .writer(member)
                .build();

        subCommentRepository.save(subComment);

        return subCommentDto;
    }

    @Override
    @Transactional
    public SubCommentDto update(SubCommentDto subCommentDto) {
        SubComment subComment = subCommentRepository.findById(subCommentDto.getId()).orElseThrow(()->
                new IllegalArgumentException("해당 글이 없습니다."));
        subComment.update(subCommentDto.getContent());

        return subCommentDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        SubComment subComment = subCommentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 글이 없습니다."));
        subCommentRepository.delete(subComment);
    }


}
