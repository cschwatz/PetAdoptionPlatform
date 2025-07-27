package com.animaladoption.platform.domain.animal;

import com.animaladoption.platform.domain.ong.Ong;
import com.animaladoption.platform.domain.person.Person;

public record AnimalGetDTO(
    String name,
    AnimalTypeEnum animalType,
    Integer age,
    AnimalGenderEnum gender,
    String race,
    Float weight,
    FurSizeEnum fur,
    String obs,
    Boolean castrated,
    Boolean adopted,
    byte[] photo,
    Ong ong,
    Person person
) {
    public AnimalGetDTO(Animal animal) {
        this(
                animal.getName(),
                animal.getAnimalType(),
                animal.getAge(),
                animal.getGender(),
                animal.getRace(),
                animal.getWeight(),
                animal.getFur(),
                animal.getObs(),
                animal.getCastrated(),
                animal.getAdopted(),
                animal.getPhoto(),
                animal.getOng(),
                animal.getPerson()
        );
    }
}
