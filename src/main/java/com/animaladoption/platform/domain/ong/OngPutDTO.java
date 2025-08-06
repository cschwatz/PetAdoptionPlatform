package com.animaladoption.platform.domain.ong;

import com.animaladoption.platform.domain.address.Address;
import jakarta.validation.constraints.Email;

public record OngPutDTO(
        String name,
        @Email
        String email,
        String phone,
        String pix,
        Address address
) {
    public OngPutDTO(Ong ong) {
        this(
            ong.getName(),
            ong.getEmail(),
            ong.getPhone(),
            ong.getPix(),
            ong.getAddress()
        );
    }
}
