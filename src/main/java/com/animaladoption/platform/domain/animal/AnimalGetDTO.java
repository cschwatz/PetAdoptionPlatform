package com.animaladoption.platform.domain.animal;

import com.animaladoption.platform.domain.ong.Ong;
import com.animaladoption.platform.domain.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Base64;
import java.util.UUID;

public record AnimalGetDTO(
        UUID id,
        String name,
        AnimalTypeEnum animalType,
        Integer age,
        AnimalGenderEnum gender,
        String breed,
        Float weight,
        FurSizeEnum fur,
        String obs,
        Boolean castrated,
        Boolean adopted,
        @JsonIgnore byte[] photoBytes, // Raw bytes - ignored in JSON
        String photo, // Base64 string - included in JSON
        Ong ong,
        Person person
) {
    // Constructor that converts byte[] to base64 string
    public AnimalGetDTO(Animal animal) {
        this(
                animal.getId(),
                animal.getName(),
                animal.getAnimalType(),
                animal.getAge(),
                animal.getGender(),
                animal.getbreed(),
                animal.getWeight(),
                animal.getFur(),
                animal.getObs(),
                animal.getCastrated(),
                animal.getAdopted(),
                animal.getPhoto(), // raw bytes
                animal.getPhoto() != null ?
                        "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(animal.getPhoto()) :
                        null, // converted base64 string
                animal.getOng(),
                animal.getPerson()
        );
    }
}