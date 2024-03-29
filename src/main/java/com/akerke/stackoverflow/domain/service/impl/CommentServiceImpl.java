package com.akerke.stackoverflow.domain.service.impl;

import com.akerke.stackoverflow.domain.dto.CommentDTO;
import com.akerke.stackoverflow.domain.dto.CommentUpdateDTO;
import com.akerke.stackoverflow.domain.service.CommentService;
import com.akerke.stackoverflow.domain.service.UserService;
import com.akerke.stackoverflow.common.exception.EntityNotFoundException;
import com.akerke.stackoverflow.domain.mapper.CommentMapper;
import com.akerke.stackoverflow.domain.entity.Comment;
import com.akerke.stackoverflow.domain.entity.Question;
import com.akerke.stackoverflow.domain.repository.CommentRepository;
import com.akerke.stackoverflow.domain.repository.QuestionRepository;
import com.akerke.stackoverflow.domain.service.QuestionService;
import com.akerke.stackoverflow.common.util.MongoUtils;
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
    private final MongoUtils mongoUtils;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Comment.class, id));
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
        mongoUtils.removeItem(comment.getQuestion().getId(), comment, Question.class, "question_comments");
        commentRepository.deleteById(id);
        return true;
    }

    @Override
    public void update(CommentUpdateDTO commentUpdateDTO, Long id) {
        Comment comment = this.getById(id);
        commentMapper.update(commentUpdateDTO, comment);
        commentRepository.save(comment);
    }
}
