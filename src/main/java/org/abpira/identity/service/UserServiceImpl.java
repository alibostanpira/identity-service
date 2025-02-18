package org.abpira.identity.service;

import org.abpira.identity.exceptions.UserDoesNotExistsException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.abpira.identity.dto.UserRequestDTO;
import org.abpira.identity.dto.UserResponseDTO;
import org.abpira.identity.exceptions.AddressMustNotBeEmptyException;
import org.abpira.identity.exceptions.UserAlreadyExistsException;
import org.abpira.identity.mapper.UserMapper;
import org.abpira.identity.repository.AddressRepository;
import org.abpira.identity.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncryptionService passwordEncryptionService;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;


    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        userRepository.findByEmail(userRequestDTO.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistsException("User already exists");
        });
        if (userRequestDTO.getAddresses() == null || userRequestDTO.getAddresses().isEmpty()) {
            throw new AddressMustNotBeEmptyException("At least one address is required.");
        }
        var user = UserMapper.mapToUser(userRequestDTO);
        user.setPassword(passwordEncryptionService.encryptPassword(user.getPassword()));
        user = userRepository.save(user);

        return UserMapper.mapToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserDoesNotExistsException("User not found"));
        return UserMapper.mapToUserResponseDTO(user);
    }

    @Override
    public Boolean checkUserExists(Long id) {

        if (userRepository.existsById(id)) {
            return true;
        }
        return false;
    }
}
