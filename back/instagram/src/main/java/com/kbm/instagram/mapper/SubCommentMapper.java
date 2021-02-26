package com.kbm.instagram.mapper;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.domain.SubComment;
import com.kbm.instagram.dto.CommentDto;
import com.kbm.instagram.dto.SubCommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SubCommentMapper {

    SubCommentMapper INSTANCE = Mappers.getMapper(SubCommentMapper.class);


    default SubComment dtoToEntity(SubCommentDto subCommentDto) {
        return SubComment.builder()
                .id(subCommentDto.getId())
                .content(subCommentDto.getContent())
                .comment(CommentMapper.INSTANCE.dtoToEntity(subCommentDto.getCommentDto()))
                .writer(MemberMapper.INSTANCE.dtoToEntity(subCommentDto.getWriter()))
                .build();
    }

    default SubCommentDto entityToDto(SubComment subComment) {
        return SubCommentDto.builder()
                .id(subComment.getId())
                .content(subComment.getContent())
                .commentDto(CommentMapper.INSTANCE.entityToDto(subComment.getComment()))
                .writer(MemberMapper.INSTANCE.entityToDto(subComment.getWriter()))
                .build();
    }
}
