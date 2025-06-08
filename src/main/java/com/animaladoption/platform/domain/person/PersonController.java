package com.animaladoption.platform.domain.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping()
    public List<PersonGetDto> getAll() {
        List<Person> returnedList = personRepository.findAll();
        return returnedList
                .stream()
                .map(PersonGetDto::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
