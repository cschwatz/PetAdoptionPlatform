package com.animaladoption.platform.domain.event;

import java.util.List;
import java.util.UUID;

public interface EventService {
    List<EventGetDTO> getAllEvents();

    EventGetDTO getEventById(UUID id);

    EventPostDTO createNewEvent(EventPostDTO dto);

    EventPutDTO updateEvent(UUID id, EventPutDTO dto);

    void deleteEvent(UUID id);
}
