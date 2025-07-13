package com.animaladoption.platform.domain.animal;

import java.util.List;
import java.util.UUID;

public interface AnimalService {
    public List<AnimalGetDTO> getAllAnimals();

    public AnimalGetDTO getAnimalById(UUID id);

    public AnimalPostDTO createNewAnimal(AnimalPostDTO dto);

    public AnimalPutDTO updateAnimal(UUID id, AnimalPutDTO dto);

    public void deleteAnimal(UUID id);
}
