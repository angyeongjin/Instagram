package com.kbm.instagram.mapper;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    default Comment dtoToEntity(CommentDto commentDto) {
        // 코드 작성하면 될듯 !
        return Comment.builder().build();
    }

    default CommentDto entityToDto(Comment comment) {
        // 코드 작성하면 될듯 !
        return CommentDto.builder().build();
    }
}
