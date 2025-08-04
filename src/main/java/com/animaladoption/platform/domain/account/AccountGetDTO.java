package com.animaladoption.platform.domain.account;

import com.animaladoption.platform.domain.ong.Ong;
import com.animaladoption.platform.domain.person.Person;

public record AccountGetDTO(
        Ong ong,
        Person person
) {

    public AccountGetDTO(Ong ong, Person person) {
        this.ong = ong;
        this.person = person;
    }

}
