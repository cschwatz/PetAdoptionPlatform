package com.animaladoption.platform.domain.animal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {
}
