package com.kbm.instagram.mapper;

import com.kbm.instagram.domain.Member;
import com.kbm.instagram.dto.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    Member dtoToEntity(MemberDto memberDto);

    MemberDto entityToDto(Member member);
}
