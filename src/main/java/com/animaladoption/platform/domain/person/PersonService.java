package com.animaladoption.platform.domain.person;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface PersonService extends UserDetailsService {
    List<PersonGetDto> getUsers();
    PersonGetDto getUserById(UUID personId);
    PersonPostDto createUser(PersonPostDto person);
    PersonPutDTO updateUser(UUID id, PersonPutDTO dto);
    void deleteUser(UUID id);
}
