package org.abpira.identity.mapper;


import org.abpira.identity.dto.UserRequestDTO;
import org.abpira.identity.dto.UserResponseDTO;
import org.abpira.identity.entities.Address;
import org.abpira.identity.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User mapToUser(UserRequestDTO userRequestDTO) {
        User user = User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .build();
        List<Address> addresses = userRequestDTO.getAddresses().stream()
                .map(addressDTO -> {
                    Address address = AddressMapper.mapToAddress(addressDTO);
                    address.setUser(user);
                    return address;
                })
                .collect(Collectors.toList());
        user.setAddresses(addresses);

        return user;
    }

    public static UserResponseDTO mapToUserResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .addresses(user.getAddresses().stream()
                        .map(AddressMapper::mapToAddressDTO)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
