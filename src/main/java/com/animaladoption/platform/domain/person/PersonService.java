package com.animaladoption.platform.domain.person;

import java.util.List;
import java.util.UUID;

public interface PersonService {
    List<PersonGetDto> getUsers();
    PersonGetDto getUserById(UUID personId);
    PersonPostDto createUser(PersonPostDto person);
}
