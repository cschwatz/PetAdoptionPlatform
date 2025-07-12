package com.animaladoption.platform.domain.ong;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ong")
public class OngController {

    private OngService ongService;

    public OngController(OngService ongService) {
        this.ongService = ongService;
    }

    @GetMapping
    public ResponseEntity<List<OngGetDTO>> getAllOngs() {
        return ResponseEntity.ok(ongService.getAllOngs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OngGetDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ongService.getById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OngPostDTO> createNewOng(@RequestBody OngPostDTO dto) {
        return ResponseEntity.ok(ongService.createNewOng(dto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<OngPutDTO> updateOng(@PathVariable UUID id, @RequestBody OngPutDTO dto) {
        return ResponseEntity.ok(ongService.updateOng(id, dto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteOng(@PathVariable UUID id) {
        ongService.deleteOng(id);
        return ResponseEntity.noContent().build();
    }

}
