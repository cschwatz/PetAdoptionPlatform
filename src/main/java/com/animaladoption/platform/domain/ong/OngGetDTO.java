package com.animaladoption.platform.domain.ong;

import com.animaladoption.platform.domain.address.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record OngGetDTO(
        @NotBlank
        String name,
        @Email
        @NotBlank
        String email,
        @NotBlank
        Address address
) {
    public OngGetDTO(Ong ong) {
        this(
            ong.getName(),
            ong.getEmail(),
            ong.getAddress()
        );
    }
}
