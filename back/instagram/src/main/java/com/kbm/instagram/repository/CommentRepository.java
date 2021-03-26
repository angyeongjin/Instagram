package com.kbm.instagram.repository;

import com.kbm.instagram.domain.Comment;
import com.kbm.instagram.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment,Long> {
}
