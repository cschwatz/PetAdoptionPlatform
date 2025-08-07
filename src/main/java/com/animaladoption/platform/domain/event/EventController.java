package com.animaladoption.platform.domain.event;

import com.animaladoption.platform.domain.account.AccountService;
import com.animaladoption.platform.domain.ong.Ong;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
@CrossOrigin(
        origins = {"http://localhost:4200", "https://petadoptionplatformfrontend.onrender.com"},
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class EventController {

    private EventService eventService;
    private AccountService accountService;

    public EventController(EventService eventService, AccountService accountService) {
        this.eventService = eventService;
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<EventGetDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventGetDTO> getEventById(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EventPostDTO> createNewEvent(@RequestBody @Valid EventPostDTO dto) {
        Ong authenticatedOng = accountService.getAuthenticatedOng();
        return ResponseEntity.ok(eventService.createNewEvent(dto, authenticatedOng));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EventPutDTO> updateEvent(@PathVariable UUID id, @RequestBody @Valid EventPutDTO dto) {
        return ResponseEntity.ok(eventService.updateEvent(id, dto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}
