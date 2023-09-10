package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.AuthRequest;
import com.akerke.stackoverflow.dto.UserDTO;
import com.akerke.stackoverflow.dto.UserUpdateDTO;
import com.akerke.stackoverflow.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {


    User getById(Long id);

    List<User> getAll();

    void deleteUser(Long id);

    void update(UserUpdateDTO userRequest, Long id);


}