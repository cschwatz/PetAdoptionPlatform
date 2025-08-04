package com.animaladoption.platform.domain.account;

import com.animaladoption.platform.domain.ong.Ong;
import com.animaladoption.platform.domain.person.Person;
import jakarta.validation.constraints.NotBlank;

public record AccountPutDTO(
        @NotBlank
        String password
) {

    public AccountPutDTO(Person person, Ong ong) {
        this(
                person == null ? ong.getPassword() : person.getPassword()
        );
    }

}
