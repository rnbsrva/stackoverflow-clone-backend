package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.CommentDTO;
import com.akerke.stackoverflow.dto.CommentUpdateDTO;
import com.akerke.stackoverflow.model.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> getAll();

    public Comment getById(Long id);

    public Comment save (CommentDTO commentDTO);

    public boolean deleteById (Long id);

    public void update (CommentUpdateDTO commentUpdateDTO);


}
