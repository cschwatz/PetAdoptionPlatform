package com.animaladoption.platform.domain.event;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
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
        return ResponseEntity.ok(eventService.createNewEvent(dto));
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
