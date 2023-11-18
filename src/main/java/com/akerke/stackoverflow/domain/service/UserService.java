package com.akerke.stackoverflow.domain.service;

import com.akerke.stackoverflow.domain.dto.UserUpdateDTO;
import com.akerke.stackoverflow.domain.entity.User;

import java.util.List;

public interface UserService {


    User getById(Long id);

    List<User> getAll();

    void deleteUser(Long id);

    void update(UserUpdateDTO userRequest, Long id);


}