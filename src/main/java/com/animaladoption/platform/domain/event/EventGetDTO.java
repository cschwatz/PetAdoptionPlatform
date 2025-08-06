package com.animaladoption.platform.domain.event;

import com.animaladoption.platform.domain.address.Address;
import com.animaladoption.platform.domain.ong.Ong;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventGetDTO(
        @NotBlank
        UUID id,
        @NotBlank
        String name,
        @NotBlank
        EventTypeEnum eventType,
        @NotBlank
        @JsonFormat(pattern="dd/MM/yyyy HH:mm")
        LocalDateTime startDate,
        @NotBlank
        @JsonFormat(pattern="dd/MM/yyyy HH:mm")
        LocalDateTime endDate,
        String obs,
        @NotBlank
        Address address,
        @NotBlank
        Ong ong
) {
    public EventGetDTO(Event event) {
        this(
                event.getId(),
                event.getName(),
                event.getEventType(),
                event.getStartDate(),
                event.getEndDate(),
                event.getObs(),
                event.getAddress(),
                event.getOng()
        );
    }
}
