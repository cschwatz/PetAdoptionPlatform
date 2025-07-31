package com.animaladoption.platform.domain.person;

import com.animaladoption.platform.domain.address.Address;
import jakarta.validation.constraints.Email;

public record PersonPutDTO(
        String firstName,
        String middleName,
        String familyName,
        String password,
        @Email
        String email,
        String phone,
        Address address

) {

    public PersonPutDTO(Person person) {
        this(person.getFirstName(),
                person.getMiddleName(),
                person.getFamilyName(),
                person.getPassword(),
                person.getEmail(),
                person.getPhone(),
                person.getAddress());
    }

}
