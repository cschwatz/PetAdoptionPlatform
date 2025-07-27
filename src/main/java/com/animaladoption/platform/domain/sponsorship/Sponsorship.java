package com.animaladoption.platform.domain.sponsorship;

import com.animaladoption.platform.domain.animal.Animal;
import com.animaladoption.platform.domain.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="sponsorship")
public class Sponsorship {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private UUID id;

    @ManyToOne
    @JoinColumn(name="person_id", referencedColumnName="id")
    private Person person;

    @ManyToOne
    @JoinColumn(name="animal_id", referencedColumnName="id")
    private Animal animal;

    protected Sponsorship() {}

    public Sponsorship(SponsorshipPostDTO dto) {
        this.person = dto.person();
        this.animal = dto.animal();
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sponsorship that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(person, that.person) && Objects.equals(animal, that.animal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person, animal);
    }
}
