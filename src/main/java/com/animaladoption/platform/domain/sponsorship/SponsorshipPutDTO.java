package com.animaladoption.platform.domain.sponsorship;

import com.animaladoption.platform.domain.animal.Animal;
import com.animaladoption.platform.domain.person.Person;
import jakarta.validation.constraints.NotBlank;

public record SponsorshipPutDTO(
        @NotBlank
        Person person,
        @NotBlank
        Animal animal
) {
    public SponsorshipPutDTO(Sponsorship sponsorship) {
        this(
                sponsorship.getPerson(),
                sponsorship.getAnimal()
        );
    }
}
