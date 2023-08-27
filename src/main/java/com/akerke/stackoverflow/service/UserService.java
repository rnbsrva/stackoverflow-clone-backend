package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.AuthRequest;
import com.akerke.stackoverflow.dto.UserDTO;
import com.akerke.stackoverflow.model.User;

import java.util.Map;

public interface UserService {


    User findById(long id);

    Map<String, Object> save(UserDTO UserDTO);
//
//    List<User> getAllUsers();
//
//    void deleteUser(Long id);
//
//    void update(UserUpdateDTO userRequest, Long id);

    Map<String,Object> refresh(String refreshToken);

    Map<String,Object> auth(AuthRequest authRequest);

}