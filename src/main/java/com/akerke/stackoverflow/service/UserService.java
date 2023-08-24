package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.UserDTO;
import com.akerke.stackoverflow.model.User;

import java.util.List;

public interface UserService {
    User findById(long id);

    User save(UserDTO UserDTO);
//
//    List<User> getAllUsers();
//
//    void deleteUser(Long id);
//
//    void update(UserUpdateDTO userRequest, Long id);

}