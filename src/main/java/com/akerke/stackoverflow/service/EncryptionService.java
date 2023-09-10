package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.UserDTO;

public interface EncryptionService {

    public String encodeDTOToString(UserDTO dto);
    public UserDTO decodeStringToDTO(String encryptedDTO);
}
