package com.animaladoption.platform.domain.event;

import com.animaladoption.platform.domain.address.Address;
import com.animaladoption.platform.domain.ong.Ong;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="event")
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @Column(name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="event_type")
    private EventTypeEnum eventType;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="end_date")
    private LocalDateTime endDate;

    @Column(name="obs")
    private String obs;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id", referencedColumnName="id")
    private Address address;

    @ManyToOne
    @JoinColumn(name="ong_id", referencedColumnName="id")
    private Ong ong;

    protected Event() {}

    public Event(EventPostDTO dto) {
        this.name = dto.name();
        this.eventType = dto.eventType();
        this.startDate = dto.startDate();
        this.endDate = dto.endDate();
        this.address = dto.address();
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public EventTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeEnum eventType) {
        this.eventType = eventType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && eventType == event.eventType && Objects.equals(startDate, event.startDate) && Objects.equals(endDate, event.endDate) && Objects.equals(obs, event.obs) && Objects.equals(address, event.address) && Objects.equals(ong, event.ong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, eventType, startDate, endDate, obs, address, ong);
    }
}
