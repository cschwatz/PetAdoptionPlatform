package com.animaladoption.platform.domain.person;

import com.animaladoption.platform.domain.address.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="person")
public class Person implements UserDetails {

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

    protected Person() {}

    public Person(UUID id, String cpf, String name, String login, String password, String email, String phone, Address address) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(id, person.id) && Objects.equals(cpf, person.cpf) && Objects.equals(name, person.name) && Objects.equals(login, person.login) && Objects.equals(password, person.password) && Objects.equals(email, person.email) && Objects.equals(phone, person.phone) && Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, name, login, password, email, phone, address);
    }
}
