package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.UserUpdateDTO;
import com.akerke.stackoverflow.entity.User;

import java.util.List;

public interface UserService {


    User getById(Long id);

    List<User> getAll();

    void deleteUser(Long id);

    void update(UserUpdateDTO userRequest, Long id);


}