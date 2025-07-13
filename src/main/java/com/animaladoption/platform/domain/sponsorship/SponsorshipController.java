package com.animaladoption.platform.domain.sponsorship;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sponsorship")
public class SponsorshipController {

    private SponsorshipService sponsorshipService;

    public SponsorshipController(SponsorshipService sponsorshipService) {
        this.sponsorshipService = sponsorshipService;
    }

    @GetMapping
    public ResponseEntity<List<SponsorshipGetDTO>> getAllSponsorships() {
        return ResponseEntity.ok(sponsorshipService.getAllSponsorships());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SponsorshipGetDTO> getSponsorshipById(@PathVariable UUID id) {
        return ResponseEntity.ok(sponsorshipService.getSponsorshipById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<SponsorshipPostDTO> createNewSponsorship(@RequestBody SponsorshipPostDTO dto) {
        return ResponseEntity.ok(sponsorshipService.createNewSponsorship(dto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SponsorshipPutDTO> updateSponsorship(@PathVariable UUID id, @RequestBody SponsorshipPutDTO dto) {
        return ResponseEntity.ok(sponsorshipService.updateSponsorship(id, dto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteSponsorship(@PathVariable UUID id) {
        sponsorshipService.deleteSponsorship(id);
        return ResponseEntity.noContent().build();
    }
}
