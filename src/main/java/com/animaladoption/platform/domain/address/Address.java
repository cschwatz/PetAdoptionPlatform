package com.animaladoption.platform.domain.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private UUID id;

    @Column(name="state")
    private String state;

    @Column(name="city")
    private String city;

    @Column(name="neighbourhood")
    private String neighborhood;

    @Column(name="street")
    private String street;

    @Column(name="num")
    private Integer number;

    @Column(name="cep")
    private String cep;

    public Address(AddressPostDTO dto) {
        this.state = dto.state();
        this.city = dto.city();
        this.neighborhood = dto.neighborhood();
        this.street = dto.street();
        this.number = dto.num();
        this.cep = dto.cep();
    }

    public Address(UUID id, String state, String city, String neighborhood, String street, Integer number, String cep) {
        this.id = id;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNeighborhood(String neighbourhood) {
        this.neighborhood = neighbourhood;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return number == address.number && Objects.equals(id, address.id) && Objects.equals(state, address.state) && Objects.equals(city, address.city) && Objects.equals(neighborhood, address.neighborhood) && Objects.equals(street, address.street) && Objects.equals(cep, address.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, city, neighborhood, street, number, cep);
    }
}
