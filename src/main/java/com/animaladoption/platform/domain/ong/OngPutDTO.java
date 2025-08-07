package com.animaladoption.platform.domain.ong;

import com.animaladoption.platform.domain.address.Address;
import jakarta.validation.constraints.Email;

public record OngPutDTO(
        String name,
        @Email
        String email,
        String phone,
        String pix,
        String instagram,
        String facebook,
        String tiktok,
        Address address
) {
    public OngPutDTO(Ong ong) {
        this(
            ong.getName(),
            ong.getEmail(),
            ong.getPhone(),
            ong.getPix(),
            ong.getInstagram(),
            ong.getFacebook(),
            ong.getTiktok(),
            ong.getAddress()
        );
    }
}
