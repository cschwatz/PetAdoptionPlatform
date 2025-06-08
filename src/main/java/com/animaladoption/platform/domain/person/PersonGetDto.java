package com.animaladoption.platform.domain.person;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PersonGetDto(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone
) {

    public PersonGetDto(Person person) {
        this(person.getName(),
            person.getEmail(),
            person.getPhone());
    }
}
