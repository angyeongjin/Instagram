package com.kbm.instagram.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubCommentMapper {

    SubCommentMapper INSTANCE = Mappers.getMapper(SubCommentMapper.class);

}
