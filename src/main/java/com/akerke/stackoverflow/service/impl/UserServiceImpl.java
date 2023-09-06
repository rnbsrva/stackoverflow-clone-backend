package com.akerke.stackoverflow.service.impl;

import com.akerke.stackoverflow.constants.Role;
import com.akerke.stackoverflow.dto.AuthRequest;
import com.akerke.stackoverflow.dto.TokenResponse;
import com.akerke.stackoverflow.dto.UserDTO;
import com.akerke.stackoverflow.dto.UserUpdateDTO;
import com.akerke.stackoverflow.exception.EntityNotFoundException;
import com.akerke.stackoverflow.mapper.UserMapper;
import com.akerke.stackoverflow.model.Question;
import com.akerke.stackoverflow.model.User;
import com.akerke.stackoverflow.repository.UserRepository;
import com.akerke.stackoverflow.security.CustomUserDetailsService;
import com.akerke.stackoverflow.security.JwtUtil;
import com.akerke.stackoverflow.service.QuestionService;
import com.akerke.stackoverflow.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.akerke.stackoverflow.constants.TokenType.ACCESS;
import static com.akerke.stackoverflow.constants.TokenType.REFRESH;
import static com.akerke.stackoverflow.util.MapUtils.toClaims;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;
    private final CustomUserDetailsService userDetailsService;
    private final UserMapper userMapper;
    private final QuestionService questionService;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException(User.class, id));
    }

    @Override
    public Map<String, Object> save(UserDTO userDTO) {
        User user = userMapper.toModel(userDTO, passwordEncoder.encode(userDTO.password()));
        userRepository.save(user);
        return Map.of("user", user, "tokens", tokenResponse(user));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        User user = this.getById(id);
        var list = user.getQuestions().stream().map(Question::getId).toList();
        list.forEach(questionService::deleteById);

        userRepository.delete(user);
    }

    @Override
    public void update(UserUpdateDTO userRequest, Long id) {
        User user = this.getById(id);
        userMapper.update(userRequest, user);
        if(userRequest.password()!=null)
            user.setPassword(passwordEncoder.encode(userRequest.password()));
        userRepository.save(user);
    }

    @Override
    public Map<String, Object> refresh(String refreshToken) {
        var email = jwtUtil.extractUsername(refreshToken, REFRESH);
        var user = userDetailsService.loadUserByUsername(email);
        return Map.of("tokens", tokenResponse((User) user));
    }

    @Override
    public Map<String, Object> auth(AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken ur = new
                UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password());
        var user = (User) manager.authenticate(ur).getPrincipal();
        return Map.of("tokens", tokenResponse(user));
    }

    private TokenResponse tokenResponse(User user) {
        return new TokenResponse(
                jwtUtil.generateToken(ACCESS, toClaims(user, ACCESS), user.getEmail()),
                jwtUtil.generateToken(REFRESH, toClaims(user, REFRESH), user.getEmail())
        );
    }
}
