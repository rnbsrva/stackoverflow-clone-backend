package com.akerke.stackoverflow.service.impl;

import com.akerke.stackoverflow.constants.Role;
import com.akerke.stackoverflow.dto.AuthRequest;
import com.akerke.stackoverflow.dto.TokenResponse;
import com.akerke.stackoverflow.dto.UserDTO;
import com.akerke.stackoverflow.model.User;
import com.akerke.stackoverflow.repository.UserRepository;
import com.akerke.stackoverflow.security.CustomUserDetailsService;
import com.akerke.stackoverflow.security.JwtUtil;
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

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public Map<String, Object> save(UserDTO userDTO) {
        User user = new User(
                userDTO.name(),
                userDTO.surname(),
                userDTO.email(),
                userDTO.password(),
                new ArrayList<>(),
                Role.ROLE_USER
        );

        userRepository.save(user);
        return Map.of("user", user, "tokens", tokenResponse(user));
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
