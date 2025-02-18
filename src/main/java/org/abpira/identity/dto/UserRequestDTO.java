package org.abpira.identity.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressDTO> addresses;
}
