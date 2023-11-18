package com.akerke.stackoverflow.domain.service;

import com.akerke.stackoverflow.domain.dto.CommentDTO;
import com.akerke.stackoverflow.domain.dto.CommentUpdateDTO;
import com.akerke.stackoverflow.domain.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAll();

    Comment getById(Long id);

    Comment save(CommentDTO commentDTO);

    boolean deleteById(Long id);

    void update(CommentUpdateDTO commentUpdateDTO, Long id);


}
