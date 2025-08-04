package com.animaladoption.platform.domain.account;

import com.animaladoption.platform.domain.ong.Ong;
import com.animaladoption.platform.domain.ong.OngService;
import com.animaladoption.platform.domain.person.Person;
import com.animaladoption.platform.domain.person.PersonService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private PersonService personService;
    private OngService ongService;

    public AccountServiceImpl(PersonService personService, OngService ongService) {
        this.personService = personService;
        this.ongService = ongService;
    }

    @Override
    public AccountGetDTO me() {
        String username = this.getUsernameByToken();
        Person person = personService.getPersonByLogin(username);
        Ong ong = ongService.getOngByLogin(username);

        return new AccountGetDTO(ong, person);
    }

    @Override
    public String getUsernameByToken() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public AccountPutDTO updatePassword(UUID id, AccountPutDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O usuário informado é inválido");
        }

        String username = this.getUsernameByToken();
        Person person = personService.getPersonByLogin(username);
        Ong ong = ongService.getOngByLogin(username);
        if(!dto.password().isBlank()) {
            this.hashAndSetNewPassword(dto.password(), person, ong);
        }

        return new AccountPutDTO(person, ong);
    }

    private void hashAndSetNewPassword(String password, Person personToUpdate, Ong ongToUpdate) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);
        if (personToUpdate == null) {
            ongToUpdate.setPassword(hashedPassword);
        } else {
            personToUpdate.setPassword(hashedPassword);
        }
    }


}
