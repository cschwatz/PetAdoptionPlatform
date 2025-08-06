package com.animaladoption.platform.domain.ong;

import com.animaladoption.platform.domain.address.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record OngPostDTO(
        @NotBlank
        String cnpj,
        @NotBlank
        String name,
        @NotBlank
        String login,
        @NotBlank
        String password,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String phone,
        String pix,
        @NotBlank
        Address address
) {
    OngPostDTO(Ong ong) {
        this(
                ong.getCnpj(),
                ong.getName(),
                ong.getLogin(),
                ong.getPassword(),
                ong.getEmail(),
                ong.getPhone(),
                ong.getPix(),
                ong.getAddress()
        );
    }
}
