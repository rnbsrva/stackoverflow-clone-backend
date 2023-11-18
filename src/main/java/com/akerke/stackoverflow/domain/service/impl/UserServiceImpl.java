package com.akerke.stackoverflow.domain.service.impl;

import com.akerke.stackoverflow.domain.dto.UserUpdateDTO;
import com.akerke.stackoverflow.domain.service.QuestionService;
import com.akerke.stackoverflow.domain.service.UserService;
import com.akerke.stackoverflow.dto.*;
import com.akerke.stackoverflow.domain.entity.Answer;
import com.akerke.stackoverflow.common.exception.EntityNotFoundException;
import com.akerke.stackoverflow.domain.mapper.UserMapper;
import com.akerke.stackoverflow.domain.entity.Question;
import com.akerke.stackoverflow.domain.entity.User;
import com.akerke.stackoverflow.domain.repository.UserRepository;
import com.akerke.stackoverflow.common.util.MongoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final QuestionService questionService;
    private final MongoUtils mongoUtils;


    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException(User.class, id));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        User user = this.getById(id);
        mongoUtils.removeItem(id, user, Answer.class, "liked_users");
        user.getQuestions().stream().map(Question::getId).forEach(questionService::deleteById);
        userRepository.delete(user);
    }

    @Override
    public void update(UserUpdateDTO userRequest, Long id) {
        User user = this.getById(id);
        userMapper.update(userRequest, user);
        userRepository.save(user);
    }

}
