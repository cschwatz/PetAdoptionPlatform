package com.animaladoption.platform.domain.event;

import com.animaladoption.platform.domain.address.Address;
import com.animaladoption.platform.domain.ong.Ong;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record EventGetDTO(
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
        @NotBlank
        Address address,
        @NotBlank
        Ong ong
) {
    public EventGetDTO(Event event) {
        this(
                event.getName(),
                event.getEventType(),
                event.getStartDate(),
                event.getEndDate(),
                event.getAddress(),
                event.getOng()
        );
    }
}
