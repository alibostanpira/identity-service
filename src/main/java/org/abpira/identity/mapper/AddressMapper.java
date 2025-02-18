package org.abpira.identity.mapper;

import org.abpira.identity.dto.AddressDTO;
import org.abpira.identity.entities.Address;

public class AddressMapper {

    public static Address mapToAddress(AddressDTO addressDTO) {
        return Address.builder()
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .country(addressDTO.getCountry())
                .zipCode(addressDTO.getZipCode())
                .build();
    }

    public static AddressDTO mapToAddressDTO(Address address) {
        return AddressDTO.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();
    }
}
