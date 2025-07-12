package com.animaladoption.platform.domain.ong;

import com.animaladoption.platform.domain.address.Address;
import jakarta.validation.constraints.Email;

public record OngPutDTO(
        String name,
        String password,
        @Email
        String email,
        String phone,
        Address address
) {
    public OngPutDTO(Ong ong) {
        this(
            ong.getName(),
            ong.getPassword(),
            ong.getEmail(),
            ong.getPhone(),
            ong.getAddress()
        );
    }
}
