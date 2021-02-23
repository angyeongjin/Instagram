package com.kbm.instagram.service;

import com.kbm.instagram.dto.FeedDto;
import com.kbm.instagram.dto.MemberDto;

import java.util.List;

public interface LikeService {

    public List<MemberDto> findLikeList(long feedId);
    public List<MemberDto> like(FeedDto feedDto, MemberDto memberDto);
}
