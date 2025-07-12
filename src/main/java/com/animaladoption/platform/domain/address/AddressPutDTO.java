package com.animaladoption.platform.domain.address;

import jakarta.validation.constraints.NotBlank;
// TODO --  CEP validation
public record AddressPutDTO (
        String state,
        String city,
        String neighborhood,
        String street,
        Integer num,
        String cep
) {
    public AddressPutDTO (Address address) {
        this(address.getState(),
                address.getCity(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getNumber(),
                address.getCep());
    }
}
