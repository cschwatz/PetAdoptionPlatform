package com.animaladoption.platform.domain.event;

import com.animaladoption.platform.domain.address.Address;
import com.animaladoption.platform.domain.ong.Ong;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventPostDTO(
        @NotBlank
        String name,
        @NotNull
        EventTypeEnum eventType,
        @NotNull
        @JsonFormat(pattern="dd/MM/yyyy HH:mm")
        LocalDateTime startDate,
        @NotNull
        @JsonFormat(pattern="dd/MM/yyyy HH:mm")
        LocalDateTime endDate,
        String obs,
        @NotNull
        Address address
) {
        public EventPostDTO(Event event) {
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
