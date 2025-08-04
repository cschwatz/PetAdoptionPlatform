package com.animaladoption.platform.infra.security;

import com.animaladoption.platform.domain.ong.OngRepository;
import com.animaladoption.platform.domain.person.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UnifiedUserDetailsService implements UserDetailsService {

    private PersonRepository personRepository;
    private OngRepository ongRepository;

    public UnifiedUserDetailsService(PersonRepository personRepository, OngRepository ongRepository) {
        this.personRepository = personRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = personRepository.findByLogin(username);
        if (user != null) {
            return user;
        }
        user = ongRepository.findByLogin(username);
        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}
