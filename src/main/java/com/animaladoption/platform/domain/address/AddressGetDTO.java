package com.animaladoption.platform.domain.address;

import jakarta.validation.constraints.NotBlank;

// TODO - CEP custom validation
public record AddressGetDTO(
        @NotBlank
        String state,
        @NotBlank
        String city,
        @NotBlank
        String neighborhood,
        @NotBlank
        String street,
        @NotBlank
        int number,
        @NotBlank
        String cep
) {

    public AddressGetDTO(Address address) {
        this(address.getState(),
                address.getCity(),
                address.getNeighbourhood(),
                address.getStreet(),
                address.getNumber(),
                address.getCep());
    }

}
