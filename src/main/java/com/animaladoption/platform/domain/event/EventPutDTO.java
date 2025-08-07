package com.animaladoption.platform.domain.event;

import com.animaladoption.platform.domain.address.Address;
import com.animaladoption.platform.domain.ong.Ong;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventPutDTO(
        String name,
        EventTypeEnum eventType,
        @JsonFormat(pattern="dd/MM/yyyy HH:mm")
        LocalDateTime startDate,
        @JsonFormat(pattern="dd/MM/yyyy HH:mm")
        LocalDateTime endDate,
        String obs,
        Address address
) {

    public EventPutDTO(Event event) {
        this(
                event.getName(),
                event.getEventType(),
                event.getStartDate(),
                event.getEndDate(),
                event.getObs(),
                event.getAddress()
        );
    }

}
