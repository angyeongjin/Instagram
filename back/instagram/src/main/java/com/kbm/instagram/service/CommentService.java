package com.kbm.instagram.service;


import com.kbm.instagram.dto.CommentDto;

public interface CommentService {

    public CommentDto create(CommentDto commentDto);
    public CommentDto update(CommentDto commentDto);
    public void delete(Long id);
    public CommentDto findById(Long id);

}
