package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.AuthRequest;
import com.akerke.stackoverflow.dto.UserDTO;

import java.util.Map;

public interface AuthService {

    void register(UserDTO userDTO);

    Map<String, Object> save(String data);

    Map<String,Object> refresh(String refreshToken);

    Map<String,Object> auth(AuthRequest authRequest);

    void forgotPassword (String email);

    void resetPassword(String token, String newPassword);
}
