package com.akerke.stackoverflow.service.impl;

import com.akerke.stackoverflow.dto.*;
import com.akerke.stackoverflow.exception.EntityNotFoundException;
import com.akerke.stackoverflow.mail.EmailDetails;
import com.akerke.stackoverflow.mail.EmailService;
import com.akerke.stackoverflow.mapper.UserMapper;
import com.akerke.stackoverflow.model.Question;
import com.akerke.stackoverflow.model.User;
import com.akerke.stackoverflow.repository.UserRepository;
import com.akerke.stackoverflow.security.CustomUserDetailsService;
import com.akerke.stackoverflow.security.JwtUtil;
import com.akerke.stackoverflow.service.QuestionService;
import com.akerke.stackoverflow.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.akerke.stackoverflow.constants.TokenType.*;
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
    private final EmailService emailService;
    private final EncryptionService encryptionService;


    @SneakyThrows
    @Override
    public void register(UserDTO userDTO) {
        String text = "http://localhost:8080/auth/verification?data="+encryptionService.encodeDTOToString(userDTO);
        EmailDetails emailDetails =  new EmailDetails(userDTO.email(), text, "Verify your email", "");
        emailService.sendSimpleMail(emailDetails);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException(User.class, id));
    }

    @SneakyThrows
    @Override
    public Map<String, Object> save(String data) {
        UserDTO userDTO = encryptionService.decodeStringToDTO(data);
        if(userDTO.isSentWithin5Minutes()){
            User user = userMapper.toModel(userDTO, passwordEncoder.encode(userDTO.password()));
            userRepository.save(user);
            return Map.of("user", user, "tokens", tokenResponse(user));
        }
        return Map.of("error", "link is expired");
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

    @Override
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        String token = jwtUtil.generateToken(RESET_PASSWORD, toClaims(user, RESET_PASSWORD), email);
        user.setResetPasswordToken(token);
        userRepository.save(user);
        String text = "http://localhost:8080/auth/reset-password?data=" + token;
        EmailDetails emailDetails =  new EmailDetails(email, text, "Reset password", "");
        emailService.sendSimpleMail(emailDetails);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        var email = jwtUtil.extractUsername(token, RESET_PASSWORD);
        var user = userRepository.findByEmail(email).orElseThrow();
        var userDetails = userDetailsService.loadUserByUsername(email);
        if(user.getResetPasswordToken()!=null && jwtUtil.isTokenValid(token, userDetails, RESET_PASSWORD)){
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetPasswordToken(null);
            userRepository.save(user);
        }
    }

    private TokenResponse tokenResponse(User user) {
        return new TokenResponse(
                jwtUtil.generateToken(ACCESS, toClaims(user, ACCESS), user.getEmail()),
                jwtUtil.generateToken(REFRESH, toClaims(user, REFRESH), user.getEmail())
        );
    }


}
