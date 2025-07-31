package com.animaladoption.platform.domain.sponsorship;

import com.animaladoption.platform.infra.exceptions.ObjectNotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SponsorshipServiceImpl implements SponsorshipService {

    private SponsorshipRepository repository;

    public SponsorshipServiceImpl(SponsorshipRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SponsorshipGetDTO> getAllSponsorships() {
        List<Sponsorship> allSponsorships = repository.findAll();
        return allSponsorships
                .stream()
                .map(SponsorshipGetDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public SponsorshipGetDTO getSponsorshipById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O Apadrinhamento informado não é válido");
        }

        Optional<Sponsorship> sponsorshipOpt = repository.findById(id);
        if (sponsorshipOpt.isEmpty()) {
            throw new ObjectNotFound("O Apadrinhamento informado não foi encontrado");
        }

        return new SponsorshipGetDTO(sponsorshipOpt.get());
    }

    @Override
    public SponsorshipPostDTO createNewSponsorship(SponsorshipPostDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O Apadrinhamento informado não é válido");
        }

        Sponsorship sponsorshipEntity = new Sponsorship(dto);
        Sponsorship savedSponsorship = repository.save(sponsorshipEntity);
        return new SponsorshipPostDTO(savedSponsorship);
    }

    @Override
    public SponsorshipPutDTO updateSponsorship(UUID id, SponsorshipPutDTO dto) {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("O Apadrinhamento informado não é válido");
        }

        Optional<Sponsorship> sponsorshipOpt = repository.findById(id);
        if (sponsorshipOpt.isEmpty()) {
            throw new ObjectNotFound("O Apadrinhamento informado não foi encontrado");
        }
        Sponsorship sponsorshipToUpdate = sponsorshipOpt.get();

        if (dto.person() != null) {
            sponsorshipToUpdate.setPerson(dto.person());
        }

        if (dto.animal() != null) {
            sponsorshipToUpdate.setAnimal(dto.animal());
        }

        Sponsorship savedSponsorship = repository.save(sponsorshipToUpdate);
        return new SponsorshipPutDTO(savedSponsorship);
    }

    @Override
    public void deleteSponsorship(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O Apadrinhamento informado não é válido");
        }

        Optional<Sponsorship> sponsorshipOpt = repository.findById(id);
        if (sponsorshipOpt.isEmpty()) {
            throw new ObjectNotFound("O Apadrinhamento informado não foi encontrado");
        }

        repository.delete(sponsorshipOpt.get());
    }
}
