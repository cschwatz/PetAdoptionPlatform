package com.animaladoption.platform.domain.ong;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OngRepository extends JpaRepository<Ong, UUID> {
    UserDetails findByLogin(String login);
    Optional<Ong> findOngByLogin(String login);
    Optional<Ong> findOngByEmail(String email);
}
