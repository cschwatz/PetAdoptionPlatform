package com.animaladoption.platform.domain.address;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="state")
    private String state;

    @Column(name="city")
    private String city;

    @Column(name="neighbourhood")
    private String neighbourhood;

    @Column(name="street")
    private String street;

    @Column(name="num")
    private int number;

    @Column(name="cep")
    private String cep;

}
