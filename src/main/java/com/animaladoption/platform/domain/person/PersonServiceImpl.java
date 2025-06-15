package com.animaladoption.platform.domain.person;

import com.animaladoption.platform.exceptions.ObjectNotFound;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<PersonGetDto> getUsers() {
        List<Person> returnedList = repository.findAll();
        return returnedList
                .stream()
                .map(PersonGetDto::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public PersonGetDto getUserById(UUID id) {
        Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isEmpty()) {
            throw new ObjectNotFound("O usuário informado não existe.");
        }

        return new PersonGetDto(personOptional.get());
    }

    @Override
    public PersonPostDto createUser(PersonPostDto personDTO) {
        if (personDTO == null) {
            throw new IllegalArgumentException("Usuário informado é inválido.");
        }

        // TODO - Check if login/email are already taken
        // TODO - encryption of password after dealing with Spring security

        Person personEntity = new Person(personDTO);
        Person createdPerson = repository.save(personEntity);
        return new PersonPostDto(createdPerson);
    }

    @Override
    public PersonPutDTO updateUser(UUID id, PersonPutDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O DTO do usuário é inválido.");
        }
        
        Optional<Person> userToUpdateOpt = repository.findById(id);
        if (userToUpdateOpt.get() == null) {
            throw new ObjectNotFound("Não foi possível localizar o usuário informado");
        }
        Person userToUpdate = userToUpdateOpt.get();
        // TODO - transformar isto em um método auxiliar
        if (!dto.name().isBlank()) {
            userToUpdate.setName(dto.name());
        }
        
        if(!dto.password().isBlank()) {
            userToUpdate.setPassword(dto.password());
        }
        
        if(!dto.email().isBlank()) {
            userToUpdate.setEmail(dto.email());
        }
        
        if (!dto.phone().isBlank()) {
            userToUpdate.setPhone(dto.phone());
        }
        
        if (dto.address() != null) {
            userToUpdate.setAddress(dto.address());
        }
        
        Person savedUser = repository.save(userToUpdate);
        return new PersonPutDTO(savedUser);
    }

    @Override
    public void deleteUser(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O id fornecido para remoção não é válido.");
        }

        Optional<Person> userToDelete = repository.findById(id);
        if (userToDelete.isEmpty()) {
            throw new ObjectNotFound("O usuário informado não foi encontrado");
        }

        repository.delete(userToDelete.get());
    }
}
