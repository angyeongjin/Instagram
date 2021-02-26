package com.kbm.instagram.service;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.domain.SubComment;
import com.kbm.instagram.dto.SubCommentDto;
import com.kbm.instagram.mapper.SubCommentMapper;
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
        SubComment subComment = SubCommentMapper.INSTANCE.dtoToEntity(subCommentDto);
        subCommentRepository.save(subComment);
        subCommentDto.setId(subComment.getId());
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
