package com.animaladoption.platform.domain.animal;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalGetDTO>> getAllAnimals() {
        return ResponseEntity.ok(animalService.getAllAnimals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalGetDTO> getAnimalById(@PathVariable UUID id) {
        return ResponseEntity.ok(animalService.getAnimalById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AnimalPostDTO> createNewAnimal(@RequestBody @Valid AnimalPostDTO dto) {
        return ResponseEntity.ok(animalService.createNewAnimal(dto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AnimalPutDTO> updateAnimal(@PathVariable UUID id, @RequestBody @Valid AnimalPutDTO dto) {
        return ResponseEntity.ok(animalService.updateAnimal(id, dto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteAnimal(@PathVariable UUID id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }

}
