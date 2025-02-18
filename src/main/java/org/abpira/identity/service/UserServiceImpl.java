package org.abpira.identity.service;

import lombok.RequiredArgsConstructor;
import org.abpira.identity.dto.UserRequestDTO;
import org.abpira.identity.dto.UserResponseDTO;
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
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        userRepository.findByEmail(userRequestDTO.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistsException("User already exists");
        });
        var user = UserMapper.mapToUser(userRequestDTO);
        user.setPassword(passwordEncryptionService.encryptPassword(user.getPassword()));
        user = userRepository.save(user);
        if (user.getAddresses() != null && !user.getAddresses().isEmpty()) {
            addressRepository.saveAll(user.getAddresses());
        }
        return UserMapper.mapToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        return null;
    }

    @Override
    public Boolean checkUserExists(Long id) {
        return null;
    }
}
