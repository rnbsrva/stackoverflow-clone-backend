package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.AuthRequest;
import com.akerke.stackoverflow.dto.UserDTO;
import com.akerke.stackoverflow.dto.UserUpdateDTO;
import com.akerke.stackoverflow.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    void register(UserDTO userDTO);

    User getById(Long id);

    Map<String, Object> save(String data);

    List<User> getAll();

    void deleteUser(Long id);

    void update(UserUpdateDTO userRequest, Long id);

    Map<String,Object> refresh(String refreshToken);

    Map<String,Object> auth(AuthRequest authRequest);

}