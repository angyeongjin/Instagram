package com.kbm.instagram.service;


import com.kbm.instagram.dto.SubCommentDto;

public interface SubCommentService {
    public SubCommentDto create(SubCommentDto subCommentDto);
    public SubCommentDto update(SubCommentDto subCommentDto);
    public void delete(Long id);
}
