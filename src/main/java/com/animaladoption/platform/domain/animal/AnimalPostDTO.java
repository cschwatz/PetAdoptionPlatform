package com.animaladoption.platform.domain.animal;

import com.animaladoption.platform.domain.ong.Ong;
import com.animaladoption.platform.domain.person.Person;
import jakarta.validation.constraints.NotBlank;

public record AnimalPostDTO(
    String name,
    @NotBlank
    AnimalTypeEnum animalType,
    Integer age,
    AnimalGenderEnum gender,
    String race,
    String color,
    Integer size,
    Float weight,
    FurSizeEnum fur,
    String obs,
    Boolean castrated,
    @NotBlank
    Boolean adopted,
    Byte[] photo,
    @NotBlank
    Ong ong,
    Person person
) {
    public AnimalPostDTO(Animal animal) {
        this(
                animal.getName(),
                animal.getAnimalType(),
                animal.getAge(),
                animal.getGender(),
                animal.getRace(),
                animal.getColor(),
                animal.getSize(),
                animal.getWeight(),
                animal.getFur(),
                animal.getObs(),
                animal.getCastrated(),
                false,
                animal.getPhoto(),
                animal.getOng(),
                animal.getPerson()
        );
    }
}
