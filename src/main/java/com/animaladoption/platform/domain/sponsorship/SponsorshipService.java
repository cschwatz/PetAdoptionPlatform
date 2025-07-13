package com.animaladoption.platform.domain.sponsorship;

import java.util.List;
import java.util.UUID;

public interface SponsorshipService {
    List<SponsorshipGetDTO> getAllSponsorships();

    SponsorshipGetDTO getSponsorshipById(UUID id);

    SponsorshipPostDTO createNewSponsorship(SponsorshipPostDTO dto);

    SponsorshipPutDTO updateSponsorship(UUID id, SponsorshipPutDTO dto);

    void deleteSponsorship(UUID id);
}
