package com.animaladoption.platform.domain.person;

import com.animaladoption.platform.domain.address.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="person")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private UUID id;

    @Column(name="cpf")
    private String cpf;

    @Column(name="name")
    private String name;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id", referencedColumnName="id")
    private Address address;

    public Person(PersonPostDto dto) {
        this.cpf = dto.cpf();
        this.name = dto.name();
        this.login = dto.login();
        this.password = dto.password();
        this.email = dto.email();
        this.phone = dto.phone();
        this.address = dto.address();
    }

}
