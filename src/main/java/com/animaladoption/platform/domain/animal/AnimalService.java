package com.animaladoption.platform.domain.animal;

import com.animaladoption.platform.domain.ong.Ong;

import java.util.List;
import java.util.UUID;

public interface AnimalService {
    public List<AnimalGetDTO> getAllAnimals();

    public AnimalGetDTO getAnimalById(UUID id);

    public AnimalPostDTO createNewAnimal(AnimalPostDTO dto, Ong ong);

    public AnimalPutDTO updateAnimal(UUID id, AnimalPutDTO dto);

    public void deleteAnimal(UUID id);

    public List<AnimalGetDTO> getAllOngAnimals(UUID id);
}
