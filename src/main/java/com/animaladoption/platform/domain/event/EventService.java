package com.animaladoption.platform.domain.event;

import com.animaladoption.platform.domain.ong.Ong;

import java.util.List;
import java.util.UUID;

public interface EventService {
    List<EventGetDTO> getAllEvents();

    EventGetDTO getEventById(UUID id);

    EventPostDTO createNewEvent(EventPostDTO dto, Ong ong);

    EventPutDTO updateEvent(UUID id, EventPutDTO dto);

    void deleteEvent(UUID id);
}
