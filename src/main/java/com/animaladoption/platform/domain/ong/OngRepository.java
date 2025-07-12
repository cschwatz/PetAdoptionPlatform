package com.animaladoption.platform.domain.ong;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OngRepository extends JpaRepository<Ong, UUID> {
}
