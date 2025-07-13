package com.animaladoption.platform.domain.event;

import com.animaladoption.platform.exceptions.ObjectNotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository repository;

    @Override
    public List<EventGetDTO> getAllEvents() {
        List<Event> allEvents = repository.findAll();
        return allEvents
                .stream()
                .map(EventGetDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public EventGetDTO getEventById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O Evento informado é inválido");
        }

        Optional<Event> eventOpt = repository.findById(id);
        if (eventOpt.isEmpty()) {
            throw new ObjectNotFound("O Evento informado não foi encontrado");
        }

        return new EventGetDTO(eventOpt.get());
    }

    @Override
    public EventPostDTO createNewEvent(EventPostDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O Evento informado é inválido");
        }

        Event eventEntity = new Event(dto);
        Event savedEvent = repository.save(eventEntity);
        return new EventPostDTO(savedEvent);
    }

    @Override
    public EventPutDTO updateEvent(UUID id, EventPutDTO dto) {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("O Evento informado não é válido");
        }

        Optional<Event> eventOpt = repository.findById(id);
        if (eventOpt.isEmpty()) {
            throw new ObjectNotFound("O Evento informado não foi encontrado");
        }
        Event eventToUpdate = eventOpt.get();

        if (!dto.name().isBlank()) {
            eventToUpdate.setName(dto.name());
        }

        if (dto.eventType() != null) {
            eventToUpdate.setEventType(dto.eventType());
        }

        if (dto.startDate() != null) {
            eventToUpdate.setStartDate(dto.startDate());
        }

        if (dto.endDate() != null) {
            eventToUpdate.setEndDate(dto.endDate());
        }

        if (dto.address() != null) {
            eventToUpdate.setAddress(dto.address());
        }

        if (dto.ong() != null) {
            eventToUpdate.setOng(dto.ong());
        }

        Event savedEvent = repository.save(eventToUpdate);
        return new EventPutDTO(savedEvent);
    }

    @Override
    public void deleteEvent(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O Evento informado não é válido");
        }

        Optional<Event> eventOpt = repository.findById(id);
        if (eventOpt.isEmpty()) {
            throw new ObjectNotFound("O Evento informado não foi encontado");
        }

        repository.delete(eventOpt.get());
    }
}
