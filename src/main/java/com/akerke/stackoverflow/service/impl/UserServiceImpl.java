package com.akerke.stackoverflow.service.impl;

import com.akerke.stackoverflow.constants.Role;
import com.akerke.stackoverflow.dto.UserDTO;
import com.akerke.stackoverflow.model.User;
import com.akerke.stackoverflow.repository.UserRepository;
import com.akerke.stackoverflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = new User(
                userDTO.name(),
                userDTO.surname(),
                userDTO.email(),
                userDTO.password(),
                new ArrayList<>(),
                Role.ROLE_USER,
                LocalDateTime.now()
        );
        return userRepository.save(user);
    }
}
