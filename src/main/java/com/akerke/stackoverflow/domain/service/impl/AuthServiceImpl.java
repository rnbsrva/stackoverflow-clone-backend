package com.akerke.stackoverflow.domain.service.impl;

import com.akerke.stackoverflow.domain.dto.AuthRequest;
import com.akerke.stackoverflow.domain.dto.TokenResponse;
import com.akerke.stackoverflow.domain.dto.UserDTO;
import com.akerke.stackoverflow.domain.service.EncryptionService;
import com.akerke.stackoverflow.common.exception.EntityNotFoundException;
import com.akerke.stackoverflow.mail.EmailDetails;
import com.akerke.stackoverflow.domain.mapper.UserMapper;
import com.akerke.stackoverflow.domain.entity.User;
import com.akerke.stackoverflow.domain.repository.UserRepository;
import com.akerke.stackoverflow.security.CustomUserDetailsService;
import com.akerke.stackoverflow.security.JwtUtil;
import com.akerke.stackoverflow.domain.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

import static com.akerke.stackoverflow.common.constants.TokenType.*;
import static com.akerke.stackoverflow.common.constants.TokenType.ACCESS;
import static com.akerke.stackoverflow.common.util.MapUtils.toClaims;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;
    private final CustomUserDetailsService userDetailsService;
    private final UserMapper userMapper;
    private final EncryptionService encryptionService;
    private final KafkaTemplate<String, Object> kafkaTemplate;



    @SneakyThrows
    @Override
    public void register(UserDTO userDTO) {
        String text = "http://localhost:8080/auth/verification?data="+encryptionService.encodeDTOToString(userDTO);
        EmailDetails emailDetails =  new EmailDetails(userDTO.email(), text, "Verify your email", "");
        sendEmail("email_verification", emailDetails);
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
        return null;
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
        User user = userRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException(User.class, email));
        String token = jwtUtil.generateToken(RESET_PASSWORD, toClaims(user, RESET_PASSWORD), email);
        user.setResetPasswordToken(token);
        userRepository.save(user);
        String text = "http://localhost:8080/auth/reset-password?data=" + token;
        EmailDetails emailDetails =  new EmailDetails(email, text, "Reset password", "");
        sendEmail("reset_password", emailDetails);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        var email = jwtUtil.extractUsername(token, RESET_PASSWORD);
        var user = userRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException(User.class, email));
        var userDetails = userDetailsService.loadUserByUsername(email);
        if(user.getResetPasswordToken()!=null && Objects.equals(token, user.getResetPasswordToken()) && jwtUtil.isTokenValid(token, userDetails, RESET_PASSWORD)){
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

    private void sendEmail(String topic, EmailDetails emailDetails){
        kafkaTemplate.send(topic, emailDetails);
    }

}
