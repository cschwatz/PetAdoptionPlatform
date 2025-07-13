package com.animaladoption.platform.domain.sponsorship;

import com.animaladoption.platform.domain.animal.Animal;
import com.animaladoption.platform.domain.person.Person;
import jakarta.validation.constraints.NotBlank;

public record SponsorshipGetDTO(
        @NotBlank
        Person person,
        @NotBlank
        Animal animal
) {

    public SponsorshipGetDTO(Sponsorship sponsorship) {
        this(
                sponsorship.getPerson(),
                sponsorship.getAnimal()
        );
    }

}
