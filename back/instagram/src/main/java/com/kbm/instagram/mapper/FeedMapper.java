package com.kbm.instagram.mapper;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.domain.Feed;
import com.kbm.instagram.dto.CommentDto;
import com.kbm.instagram.dto.FeedDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface FeedMapper {

    FeedMapper INSTANCE = Mappers.getMapper(FeedMapper.class);

    default Feed dtoToEntity(FeedDto feedDto) {
        List<Comment> commentList = new ArrayList<>();
        for (CommentDto commentDto : feedDto.getCommentList()) {
            commentList.add(CommentMapper.INSTANCE.dtoToEntity(commentDto));
        }
        return Feed.builder()
                .id(feedDto.getId())
                .contents(feedDto.getContents())
                .filter(feedDto.getFilter())
                .images(feedDto.getImages())
                .writer(MemberMapper.INSTANCE.dtoToEntity(feedDto.getWriter()))
                .commentList(commentList).build();
    }

    default FeedDto entityToDto(Feed feed) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : feed.getCommentList()) {
            commentDtoList.add(CommentMapper.INSTANCE.entityToDto(comment));
        }
        return FeedDto.builder()
                .id(feed.getId())
                .contents(feed.getContents())
                .filter(feed.getFilter())
                .images(feed.getImages())
                .writer(MemberMapper.INSTANCE.entityToDto(feed.getWriter()))
                .commentList(commentDtoList)
                .createdDate(feed.getCreatedDate()).build();
    }
}
