package com.animaladoption.platform.domain.ong;

import com.animaladoption.platform.domain.address.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="ong")
public class Ong {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private UUID id;

    @Column(name="cnpj")
    private String cpnj;

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

    @OneToOne
    @JoinColumn(name="address_id", referencedColumnName="id")
    private Address address;

    public Ong(UUID id, String cpnj, String name, String login, String password, String email, Address address) {
        this.id = id;
        this.cpnj = cpnj;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public Ong(OngPostDTO dto) {
        this.cpnj = dto.cnpj();
        this.name = dto.name();
        this.login = dto.login();
        this.password = dto.password();
        this.email = dto.email();
        this.phone = dto.phone();
        this.address = dto.address();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpnj() {
        return cpnj;
    }

    public void setCpnj(String cpnj) {
        this.cpnj = cpnj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ong ong)) return false;
        return Objects.equals(id, ong.id) && Objects.equals(cpnj, ong.cpnj) && Objects.equals(name, ong.name) && Objects.equals(login, ong.login) && Objects.equals(password, ong.password) && Objects.equals(email, ong.email) && Objects.equals(phone, ong.phone) && Objects.equals(address, ong.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpnj, name, login, password, email, phone, address);
    }
}
