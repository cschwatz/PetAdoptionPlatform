package com.animaladoption.platform.domain.animal;

import com.animaladoption.platform.domain.ong.Ong;
import com.animaladoption.platform.domain.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="animal")
public class Animal {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="animal_type")
    @Enumerated(EnumType.STRING)
    private AnimalTypeEnum animalType;

    @Column(name="age")
    private Integer age;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private AnimalGenderEnum gender;

    @Column(name="breed")
    private String breed;

    @Column(name="color")
    private String color;

    @Column(name="size")
    private Integer size;

    @Column(name="weight")
    private Float weight;

    @Column(name="fur")
    @Enumerated(EnumType.STRING)
    private FurSizeEnum fur;

    @Column(name="obs")
    private String obs;

    @Column(name="castrated")
    private Boolean castrated;

    @Column(name="adopted")
    private Boolean adopted;

    @Column(name="photo")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name="ong_id", referencedColumnName="id")
    private Ong ong;

    @ManyToOne
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private Person person;

    protected Animal() {}

    public Animal(AnimalPostDTO dto) {
        this.name = dto.name();
        this.animalType = dto.animalType();
        this.age = dto.age();
        this.gender = dto.gender();
        this.breed = dto.breed();
        this.color = dto.color();
        this.size = dto.size();
        this.weight = dto.weight();
        this.fur = dto.fur();
        this.obs = dto.obs();
        this.castrated = dto.castrated();
        this.adopted = dto.adopted();
        this.photo = dto.photo();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalTypeEnum getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalTypeEnum animalType) {
        this.animalType = animalType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getbreed() {
        return breed;
    }

    public void setbreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public FurSizeEnum getFur() {
        return fur;
    }

    public void setFur(FurSizeEnum fur) {
        this.fur = fur;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Boolean getCastrated() {
        return castrated;
    }

    public void setCastrated(Boolean castrated) {
        this.castrated = castrated;
    }

    public Boolean getAdopted() {
        return adopted;
    }

    public void setAdopted(Boolean adopted) {
        this.adopted = adopted;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public AnimalGenderEnum getGender() {
        return gender;
    }

    public void setGender(AnimalGenderEnum gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return Objects.equals(id, animal.id) && Objects.equals(name, animal.name) && animalType == animal.animalType && Objects.equals(age, animal.age) && gender == animal.gender && Objects.equals(breed, animal.breed) && Objects.equals(color, animal.color) && Objects.equals(size, animal.size) && Objects.equals(weight, animal.weight) && fur == animal.fur && Objects.equals(obs, animal.obs) && Objects.equals(castrated, animal.castrated) && Objects.equals(adopted, animal.adopted) && Objects.deepEquals(photo, animal.photo) && Objects.equals(ong, animal.ong) && Objects.equals(person, animal.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, animalType, age, gender, breed, color, size, weight, fur, obs, castrated, adopted, Arrays.hashCode(photo), ong, person);
    }

    public UUID getId() {
        return id;
    }
}
