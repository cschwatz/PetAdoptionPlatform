package com.animaladoption.platform.domain.animal;

import com.animaladoption.platform.domain.ong.Ong;
import com.animaladoption.platform.domain.person.Person;
import jakarta.validation.constraints.NotBlank;

import java.util.Locale;

public record AnimalPutDTO(
        String name,
        AnimalTypeEnum animalType,
        Integer age,
        AnimalGenderEnum gender,
        String breed,
        String color,
        Integer size,
        Float weight,
        FurSizeEnum fur,
        String obs,
        Boolean castrated,
        Boolean adopted,
        byte[] photo,
        Person person
) {
    public AnimalPutDTO(Animal animal) {
        this(
                animal.getName(),
                animal.getAnimalType(),
                animal.getAge(),
                animal.getGender(),
                animal.getbreed(),
                animal.getColor(),
                animal.getSize(),
                animal.getWeight(),
                animal.getFur(),
                animal.getObs(),
                animal.getCastrated(),
                animal.getAdopted(),
                animal.getPhoto(),
                animal.getPerson()
        );
    }
}
