package com.animaladoption.platform.domain.person;

import com.animaladoption.platform.infra.exceptions.ObjectNotFound;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        if (isEmailAlreadyTaken(personDTO.email())) {
            throw new IllegalArgumentException("E-mail informado já existe");
        }

        Person personEntity = new Person(personDTO);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(personEntity.getPassword());
        personEntity.setPassword(hashedPassword);
        Person createdPerson = repository.save(personEntity);
        return new PersonPostDto(createdPerson);
    }

    @Override
    public PersonPutDTO updateUser(UUID id, PersonPutDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O DTO do usuário é inválido.");
        }
        
        Optional<Person> userToUpdateOpt = repository.findById(id);
        if (userToUpdateOpt.isEmpty()) {
            throw new ObjectNotFound("Não foi possível localizar o usuário informado");
        }
        Person userToUpdate = userToUpdateOpt.get();
        // TODO - transformar isto em um método auxiliar
        if (!dto.firstName().isBlank()) {
            userToUpdate.setFirstName(dto.firstName());
        }

        if (!dto.middleName().isBlank()) {
            userToUpdate.setMiddleName(dto.middleName());
        }

        if (!dto.familyName().isBlank()) {
            userToUpdate.setFamilyName(dto.familyName());
        }

        if(!dto.password().isBlank()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(dto.password());
            userToUpdate.setPassword(hashedPassword);
        }
        
        if(!dto.email().isBlank()) {
            if (!isEmailAlreadyTaken(dto.email())) {
                userToUpdate.setEmail(dto.email());
            } else {
                throw new IllegalArgumentException("O E-mail informado já existe");
            }
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    private boolean isEmailAlreadyTaken(String email) {
        Optional<Person> personOpt = repository.findPersonByEmail(email);
        return personOpt.isPresent();
    }
}
