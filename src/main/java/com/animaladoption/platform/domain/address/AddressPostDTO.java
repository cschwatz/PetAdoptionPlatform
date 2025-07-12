package com.animaladoption.platform.domain.address;

import jakarta.validation.constraints.NotBlank;
// TODO --  CEP validation
public record AddressPostDTO(
        @NotBlank
        String state,
        @NotBlank
        String city,
        @NotBlank
        String neighborhood,
        @NotBlank
        String street,
        @NotBlank
        Integer num,
        @NotBlank
        String cep
) {
        public AddressPostDTO(Address address) {
                this(address.getState(),
                        address.getCity(),
                        address.getNeighborhood(),
                        address.getStreet(),
                        address.getNumber(),
                        address.getCep());
        }
}
