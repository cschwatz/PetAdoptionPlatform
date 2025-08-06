package com.animaladoption.platform.domain.animal;

import com.animaladoption.platform.domain.ong.Ong;
import com.animaladoption.platform.domain.person.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnimalPostDTO(
    String name,
    @NotNull
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
    @NotNull
    Boolean adopted,
    byte[] photo
) {
    public AnimalPostDTO(Animal animal) {
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
                false,
                animal.getPhoto()
        );
    }
}
