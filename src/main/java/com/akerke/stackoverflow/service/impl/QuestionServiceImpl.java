package com.akerke.stackoverflow.service.impl;

import com.akerke.stackoverflow.constants.QuestionStatus;
import com.akerke.stackoverflow.dto.QuestionDTO;
import com.akerke.stackoverflow.dto.QuestionUpdateDTO;
import com.akerke.stackoverflow.exception.EntityNotFoundException;
import com.akerke.stackoverflow.mapper.QuestionMapper;
import com.akerke.stackoverflow.entity.Answer;
import com.akerke.stackoverflow.entity.Question;
import com.akerke.stackoverflow.entity.Tag;
import com.akerke.stackoverflow.entity.User;
import com.akerke.stackoverflow.repository.QuestionRepository;
import com.akerke.stackoverflow.repository.TagRepository;
import com.akerke.stackoverflow.repository.UserRepository;
import com.akerke.stackoverflow.service.AnswerService;
import com.akerke.stackoverflow.service.CommentService;
import com.akerke.stackoverflow.service.QuestionService;
import com.akerke.stackoverflow.service.TagService;
import com.akerke.stackoverflow.util.MongoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final TagService tagService;
    private final CommentService commentService;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final AnswerService answerService;
    private final MongoUtils mongoUtils;

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question getById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Question.class, id));
    }

    @Override
    public Question save(QuestionDTO questionDTO) {
        Question question = questionMapper.toModel(questionDTO);
        User user = userRepository.findById(questionDTO.userId()).orElseThrow();
        question.setUser(user);
        for (Long id: questionDTO.tagIds()) {
            question.getTags().add(tagService.getById(id));
        }

        Question question1 = questionRepository.save(question);
        user.getQuestions().add(question1);
        userRepository.save(user);

        for (Long id: questionDTO.tagIds()) {
            Tag tag = tagService.getById(id);
            tag.getQuestions().add(question1);
            tagRepository.save(tag);
        }
        return question1;
    }

    @Override
    public boolean acceptAnswer(Long questionId, Long answerId) {
        Question question = this.getById(questionId);
        if(Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), question.getUser().getEmail())) {
            Answer answer = answerService.getById(answerId);
            question.setAcceptedAnswer(answer);
            question.setStatus(QuestionStatus.QUESTION_SOLVED);
            questionRepository.save(question);
            return true;
        }
        return false;
    }

    @Override
    public void update(QuestionUpdateDTO questionUpdateDTO, Long id) {
        Question question = this.getById(id);
        questionMapper.update(questionUpdateDTO, question);
        questionRepository.save(question);
    }


    @Override
    public boolean deleteById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Question question = questionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Question.class, id));
        User owner = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        User qOwner = userRepository.findById(question.getUser().getId()).orElseThrow();
        if (qOwner.equals(owner)) {
            question.getAnswers().forEach(answer -> answerService.delete(answer.getId()));
            question.getComments().forEach(comment -> commentService.deleteById(comment.getId()));
            mongoUtils.removeItem(question.getId(), question, HTML.Tag.class, "tag_questions");
            questionRepository.delete(question);
            mongoUtils.removeItem(owner.getId(), question, User.class, "questions");
            return true;
        }
        return false;
    }
}
