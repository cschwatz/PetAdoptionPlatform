package com.animaladoption.platform.domain.animal;

import com.animaladoption.platform.domain.account.AccountService;
import com.animaladoption.platform.domain.ong.Ong;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/animal")
@CrossOrigin(
        origins = "${app.frontend.url}",
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class AnimalController {

    private AnimalService animalService;
    private AccountService accountService;

    public AnimalController(AnimalService animalService, AccountService accountService) {
        this.animalService = animalService;
        this.accountService = accountService;
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
        Ong authenticatedOng = accountService.getAuthenticatedOng();
        return ResponseEntity.ok(animalService.createNewAnimal(dto, authenticatedOng));
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
