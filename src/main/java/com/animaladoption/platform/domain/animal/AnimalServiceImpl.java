package com.animaladoption.platform.domain.animal;

import com.animaladoption.platform.infra.exceptions.ObjectNotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository repository;

    public AnimalServiceImpl(AnimalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AnimalGetDTO> getAllAnimals() {
        List<Animal> animalsList = repository.findAll();
        return animalsList
                .stream()
                .map(AnimalGetDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public AnimalGetDTO getAnimalById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O Animal informado é inválido");
        }

        Optional<Animal> animalOpt = repository.findById(id);
        if (animalOpt.isEmpty()) {
            throw new ObjectNotFound("O Animal não foi encontrado");
        }

        return new AnimalGetDTO(animalOpt.get());
    }

    @Override
    public AnimalPostDTO createNewAnimal(AnimalPostDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O Animal informado é inválido");
        }

        Animal animalEntity = new Animal(dto);
        Animal savedAnimal = repository.save(animalEntity);
        return new AnimalPostDTO(savedAnimal);
    }

    @Override
    public AnimalPutDTO updateAnimal(UUID id, AnimalPutDTO dto) {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("O Animal informado é inválido");
        }

        Optional<Animal> animalOpt = repository.findById(id);
        if (animalOpt.isEmpty()) {
            throw new ObjectNotFound("O Animal informado não foi encontrado");
        }
        Animal animalToUpdate = animalOpt.get();

        if (!dto.name().isBlank()) {
            animalToUpdate.setName(dto.name());
        }

        if (dto.animalType() != null) {
            animalToUpdate.setAnimalType(dto.animalType());
        }

        if (dto.age() > 0) {
            animalToUpdate.setAge(dto.age());
        }

        if (dto.gender() != null) {
            animalToUpdate.setGender(dto.gender());
        }

        if (!dto.breed().isBlank()) {
            animalToUpdate.setbreed(dto.breed());
        }

        if (!dto.color().isBlank()) {
            animalToUpdate.setColor(dto.color());
        }

        // Need validation for Size and Weight (to avoid bad numbers)
        if (dto.size() > 0) {
            animalToUpdate.setSize(dto.size());
        }

        if (dto.weight() > 0) {
            animalToUpdate.setWeight(dto.weight());
        }

        if (dto.fur() != null) {
            animalToUpdate.setFur(dto.fur());
        }

        if (!dto.obs().isBlank()) {
            animalToUpdate.setObs(dto.obs());
        }

        if (dto.castrated() != null) {
            animalToUpdate.setCastrated(dto.castrated());
        }

        if (dto.adopted() != null) {
            animalToUpdate.setAdopted(dto.adopted());
        }

        if (dto.photo() != null) {
            animalToUpdate.setPhoto(dto.photo());
        }

        if (dto.person() != null) {
            animalToUpdate.setPerson(dto.person());
        }

        Animal savedAnimal = repository.save(animalToUpdate);
        return new AnimalPutDTO(savedAnimal);
    }

    @Override
    public void deleteAnimal(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O Animal informado é inválido");
        }

        Optional<Animal> animalOpt = repository.findById(id);
        if (animalOpt.isEmpty()) {
            throw new ObjectNotFound("O Animal informado não foi encontrado");
        }

        repository.delete(animalOpt.get());
    }
}
