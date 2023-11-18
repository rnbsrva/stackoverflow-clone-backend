package com.akerke.stackoverflow.domain.service;

import com.akerke.stackoverflow.domain.dto.UserDTO;

public interface EncryptionService {

    public String encodeDTOToString(UserDTO dto);
    public UserDTO decodeStringToDTO(String encryptedDTO);
}
