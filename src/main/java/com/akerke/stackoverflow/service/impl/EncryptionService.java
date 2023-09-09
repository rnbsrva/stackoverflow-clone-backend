package com.akerke.stackoverflow.service.impl;

import com.akerke.stackoverflow.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class EncryptionService {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public String encodeDTOToString(UserDTO dto) throws Exception {

        String jsonData = objectMapper.writeValueAsString(dto);
        return Base64.getEncoder().encodeToString(jsonData.getBytes());

    }


    @SneakyThrows
    public UserDTO decodeStringToDTO(String encryptedDTO) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedDTO);
        String jsonData = new String(decodedBytes);
        return objectMapper.readValue(jsonData, UserDTO.class);
    }
}
