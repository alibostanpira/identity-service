package org.abpira.identity.service;


import org.abpira.identity.dto.UserRequestDTO;
import org.abpira.identity.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUser(Long id);
    Boolean checkUserExists(Long id);
}
