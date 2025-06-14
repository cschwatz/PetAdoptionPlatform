package com.animaladoption.platform.domain.person;

import com.animaladoption.platform.domain.address.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// TODO - validação de cpf e telefone
public record PersonPostDto(
        @NotBlank
        String cpf,
        @NotBlank
        String name,
        @NotBlank
        String login,
        @NotBlank
        String password,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotNull
        Address address
) {
    public PersonPostDto(Person person) {
        this(person.getCpf(),
                person.getName(),
                person.getLogin(),
                person.getPassword(),
                person.getEmail(),
                person.getPhone(),
                person.getAddress());
    }
}
