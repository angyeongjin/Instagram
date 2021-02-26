package com.kbm.instagram.mapper;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.domain.Member;
import com.kbm.instagram.domain.SubComment;
import com.kbm.instagram.dto.CommentDto;
import com.kbm.instagram.dto.SubCommentDto;
import com.kbm.instagram.service.FeedService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    default Comment dtoToEntity(CommentDto commentDto) {
        List<SubComment> subCommentList = new ArrayList<>();
       for(SubCommentDto subCommentDto : commentDto.getSubCommentList()){
            subCommentList.add(SubCommentMapper.INSTANCE.dtoToEntity(subCommentDto));
       }
        return Comment.builder()
                .id(commentDto.getId())
                .content(commentDto.getContent())
                .feed(Feed.builder().id(commentDto.getFeedId()).build())
                .subCommentList(subCommentList)
                .writer(MemberMapper.INSTANCE.dtoToEntity(commentDto.getWriter()))
                .build();
    }

    default CommentDto entityToDto(Comment comment) {
        List<SubCommentDto> subCommentDtoList = new ArrayList<>();
        for(SubComment subComment : comment.getSubCommentList()){
            subCommentDtoList.add(SubCommentMapper.INSTANCE.entityToDto(subComment));
        }

        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .feedId(comment.getFeed().getId())
                .subCommentList(subCommentDtoList)
                .writer(MemberMapper.INSTANCE.entityToDto(comment.getWriter()))
                .build();
    }
}
