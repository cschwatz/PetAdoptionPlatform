package com.animaladoption.platform.domain.ong;

import com.animaladoption.platform.domain.address.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record OngGetDTO(
        UUID id,
        @NotBlank
        String name,
        @Email
        @NotBlank
        String email,
        String pix,
        String instagram,
        String facebook,
        String tiktok,
        @NotBlank
        Address address
) {
    public OngGetDTO(Ong ong) {
        this(
            ong.getId(),
            ong.getName(),
            ong.getEmail(),
            ong.getPix(),
            ong.getInstagram(),
            ong.getFacebook(),
            ong.getTiktok(),
            ong.getAddress()
        );
    }
}
