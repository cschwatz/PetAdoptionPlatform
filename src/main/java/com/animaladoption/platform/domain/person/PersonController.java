package com.animaladoption.platform.domain.person;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private PersonService personService;

    PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonGetDto>> getAll() {
        return ResponseEntity.ok(personService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonGetDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(personService.getUserById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PersonPostDto> createNewUser(@RequestBody @Valid PersonPostDto dto) {
        return ResponseEntity.ok(personService.createUser(dto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PersonPutDTO> updateUser(@PathVariable UUID id, @RequestBody @Valid PersonPutDTO dto) {
        return ResponseEntity.ok(personService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        personService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
