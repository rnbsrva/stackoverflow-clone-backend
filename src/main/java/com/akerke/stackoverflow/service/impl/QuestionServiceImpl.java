package com.akerke.stackoverflow.service.impl;

import com.akerke.stackoverflow.dto.QuestionDTO;
import com.akerke.stackoverflow.exception.EntityNotFoundException;
import com.akerke.stackoverflow.mapper.QuestionMapper;
import com.akerke.stackoverflow.mapper.UserMapper;
import com.akerke.stackoverflow.model.Question;
import com.akerke.stackoverflow.model.User;
import com.akerke.stackoverflow.repository.QuestionRepository;
import com.akerke.stackoverflow.repository.TagRepository;
import com.akerke.stackoverflow.repository.UserRepository;
import com.akerke.stackoverflow.service.QuestionService;
import com.akerke.stackoverflow.service.TagService;
import com.akerke.stackoverflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final TagService tagService;
    private final UserRepository userRepository;

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
        return question1;
    }


    @Override
    public boolean deleteById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Question question = questionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Question.class, id));
        User owner = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        User qOwner = userRepository.findById(question.getUser().getId()).orElseThrow();
        if (qOwner.equals(owner)) {
            questionRepository.delete(question);
            owner.getQuestions().remove(question);
            userRepository.save(owner);
            return true;
        }
        return false;
    }
}
