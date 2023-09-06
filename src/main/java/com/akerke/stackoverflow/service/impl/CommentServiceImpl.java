package com.akerke.stackoverflow.service.impl;

import com.akerke.stackoverflow.dto.CommentDTO;
import com.akerke.stackoverflow.dto.CommentUpdateDTO;
import com.akerke.stackoverflow.exception.EntityNotFoundException;
import com.akerke.stackoverflow.mapper.CommentMapper;
import com.akerke.stackoverflow.model.Comment;
import com.akerke.stackoverflow.model.Question;
import com.akerke.stackoverflow.repository.CommentRepository;
import com.akerke.stackoverflow.repository.QuestionRepository;
import com.akerke.stackoverflow.service.CommentService;
import com.akerke.stackoverflow.service.QuestionService;
import com.akerke.stackoverflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final QuestionService questionService;
    private final QuestionRepository questionRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(()->new EntityNotFoundException(Comment.class, id));
    }

    @Override
    public Comment save(CommentDTO commentDTO) {
        var comment = commentMapper.toModel(commentDTO);
        var question = questionService.getById(commentDTO.questionId());

        comment.setUser(userService.getById(commentDTO.userId()));
        comment.setQuestion(question);

        var saved = commentRepository.save(comment);

        question.getComments().add(saved);
        questionRepository.save(question);

        return saved;
    }

    @Override
    public boolean deleteById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        Question question = questionRepository.findById(comment.getQuestion().getId()).orElseThrow();
        question.getComments().remove(comment);
        commentRepository.deleteById(id);
        questionRepository.save(question);
        return true;
    }

    @Override
    public void update(CommentUpdateDTO commentUpdateDTO) {

    }
}
