package com.akerke.stackoverflow.domain.service.impl;

import com.akerke.stackoverflow.domain.dto.AnswerDTO;
import com.akerke.stackoverflow.domain.dto.AnswerUpdateDTO;
import com.akerke.stackoverflow.domain.service.AnswerService;
import com.akerke.stackoverflow.common.exception.EntityNotFoundException;
import com.akerke.stackoverflow.domain.mapper.AnswerMapper;
import com.akerke.stackoverflow.domain.entity.Answer;
import com.akerke.stackoverflow.domain.entity.Question;
import com.akerke.stackoverflow.domain.entity.User;
import com.akerke.stackoverflow.domain.repository.AnswerRepository;
import com.akerke.stackoverflow.domain.repository.QuestionRepository;
import com.akerke.stackoverflow.domain.repository.UserRepository;
import com.akerke.stackoverflow.common.util.MongoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final MongoUtils mongoUtils;

    @Override
    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    @Override
    public Answer getById(Long id) {
        return answerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Answer.class, id));
    }

    @Override
    public Answer save(AnswerDTO answerDTO) {
        User user = userRepository.findById(answerDTO.userId()).orElseThrow(()-> new EntityNotFoundException(User.class,answerDTO.userId()));
        Question question = questionRepository.findById(answerDTO.questionId()).orElseThrow(()-> new EntityNotFoundException(Question.class,answerDTO.questionId()));
        Answer answer = answerMapper.toModel(answerDTO);
        answer.setUser(user);
        answer.setQuestion(question);
        var savedAnswer = answerRepository.save(answer);
        question.getAnswers().add(savedAnswer);
        questionRepository.save(question);
        return savedAnswer;
    }

    @Override
    public Answer update(AnswerUpdateDTO answerUpdateDTO, Long id) {
        Answer answer = this.getById(id);
        answerMapper.update(answerUpdateDTO, answer);
        return answerRepository.save(answer);
    }

    @Override
    public void delete(Long id) {
        Answer answer = this.getById(id);
        mongoUtils.removeItem(answer.getQuestion().getId(), answer, Question.class, "answers");
        answerRepository.delete(answer);
    }

    @Override
    public boolean likeAnswer(Long id) {
        Answer answer = this.getById(id);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException(User.class, email));
        if (answer.getUser()==user && answer.getLikedUsers().contains(user)){
            answer.getLikedUsers().add(user);
            answerRepository.save(answer);
            return true;
        }
        return false;
    }
}
