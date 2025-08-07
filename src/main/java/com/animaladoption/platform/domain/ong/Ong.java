package com.animaladoption.platform.domain.ong;

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
@Table(name="ong")
public class Ong implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="cnpj")
    private String cnpj;

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

    @Column(name="pix")
    private String pix;

    @Column(name="instagram")
    private String instagram;

    @Column(name="facebook")
    private String facebook;

    @Column(name="tiktok")
    private String tiktok;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id", referencedColumnName="id")
    private Address address;

    protected Ong() {}

    public Ong(UUID id, String cnpj, String name, String login, String password, String email, Address address) {
        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public Ong(OngPostDTO dto) {
        this.cnpj = dto.cnpj();
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTiktok() {
        return tiktok;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ong ong)) return false;
        return Objects.equals(id, ong.id) && Objects.equals(cnpj, ong.cnpj) && Objects.equals(name, ong.name) && Objects.equals(login, ong.login) && Objects.equals(password, ong.password) && Objects.equals(email, ong.email) && Objects.equals(phone, ong.phone) && Objects.equals(pix, ong.pix) && Objects.equals(instagram, ong.instagram) && Objects.equals(facebook, ong.facebook) && Objects.equals(tiktok, ong.tiktok) && Objects.equals(address, ong.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj, name, login, password, email, phone, pix, instagram, facebook, tiktok, address);
    }
}
