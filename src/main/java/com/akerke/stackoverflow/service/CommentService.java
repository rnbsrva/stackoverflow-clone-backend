package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.CommentDTO;
import com.akerke.stackoverflow.dto.CommentUpdateDTO;
import com.akerke.stackoverflow.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAll();

    Comment getById(Long id);

    Comment save(CommentDTO commentDTO);

    boolean deleteById(Long id);

    void update(CommentUpdateDTO commentUpdateDTO, Long id);


}
