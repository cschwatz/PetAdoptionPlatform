package com.animaladoption.platform.domain.person;

import com.animaladoption.platform.domain.address.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PersonPutDTO(
        String name,
        String password,
        @Email
        String email,
        String phone,
        Address address

) {

    public PersonPutDTO(Person person) {
        this(person.getName(),
                person.getPassword(),
                person.getEmail(),
                person.getPhone(),
                person.getAddress());
    }

}
