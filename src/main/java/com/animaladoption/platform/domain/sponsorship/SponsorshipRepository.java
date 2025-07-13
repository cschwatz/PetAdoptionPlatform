package com.animaladoption.platform.domain.sponsorship;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SponsorshipRepository extends JpaRepository<Sponsorship, UUID> {
}
