package com.animaladoption.platform.domain.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    UserDetails findByLogin(String login);
    Optional<Person> findPersonByEmail(String email);

    Optional<Person> findPersonByLogin(String login);
}
