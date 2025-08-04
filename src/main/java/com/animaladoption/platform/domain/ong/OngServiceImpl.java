package com.animaladoption.platform.domain.ong;

import com.animaladoption.platform.domain.account.AccountService;
import com.animaladoption.platform.domain.animal.AnimalGetDTO;
import com.animaladoption.platform.domain.animal.AnimalService;
import com.animaladoption.platform.domain.person.Person;
import com.animaladoption.platform.infra.exceptions.ObjectNotFound;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OngServiceImpl implements OngService {

    private OngRepository repository;
    private AnimalService animalService;

    public OngServiceImpl(OngRepository repository,
                          AnimalService animalService) {
        this.repository = repository;
        this.animalService = animalService;
    }

    @Override
    public List<OngGetDTO> getAllOngs() {
        List<Ong> ongsList = repository.findAll();
        return ongsList
                .stream()
                .map(OngGetDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public OngGetDTO getById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id informado inválido");
        }

        Optional<Ong> ongOpt = repository.findById(id);
        if (ongOpt.isEmpty()) {
            throw new ObjectNotFound("A ONG informada não foi encontrada");
        }

        return new OngGetDTO(ongOpt.get());
    }

    @Override
    public OngPostDTO createNewOng(OngPostDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO informado é inválido");
        }

        if (isEmailAlreadyTaken(dto.email())) {
            throw new IllegalArgumentException("E-mail informado já existe");
        }

        Ong ongEntity = new Ong(dto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(ongEntity.getPassword());
        ongEntity.setPassword(hashedPassword);
        Ong savedOng = repository.save(ongEntity);

        return new OngPostDTO(savedOng);
    }

    private boolean isEmailAlreadyTaken(String email) {
        Optional<Ong> ongOpt = repository.findOngByEmail(email);
        return ongOpt.isPresent();
    }

    @Override
    public OngPutDTO updateOng(UUID id, OngPutDTO dto) {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("A ONG informada é inválida");
        }

        Optional<Ong> ongOpt = repository.findById(id);
        if (ongOpt.isEmpty()) {
            throw new ObjectNotFound("A ONG informada não foi encontrada");
        }

        Ong ongToUpdate = ongOpt.get();

        if (!dto.name().isBlank()) {
            ongToUpdate.setName(dto.name());
        }

        if (!dto.email().isBlank() && !ongToUpdate.getEmail().equals(dto.email())) {
            ongToUpdate.setEmail(dto.email());
        }

        if (!dto.phone().isBlank()) {
            ongToUpdate.setPhone(dto.phone());
        }

        if (dto.address() != null) {
            ongToUpdate.setAddress(dto.address());
        }

        Ong updatedOng = repository.save(ongToUpdate);
        return new OngPutDTO(updatedOng);
    }

    @Override
    public void deleteOng(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ONG informada inválida");
        }

        Optional<Ong> ongOpt = repository.findById(id);
        if (ongOpt.isEmpty()) {
            throw new ObjectNotFound("A ONG informada não foi encontrada");
        }

        repository.delete(ongOpt.get());
    }

    @Override
    public Ong getOngByLogin(String login) {
        if (login.isBlank()) {
            throw new IllegalArgumentException("O nome de usuário informado é inválido");
        }

        Optional<Ong> ongOpt = repository.findOngByLogin(login);
        return ongOpt.orElse(null);
    }

    @Override
    public List<AnimalGetDTO> getMyAnimals() {
        String username = this.getUsernameByToken();
        Ong ong = this.getOngByLogin(username);

        if (ong == null) {
            throw new ObjectNotFound("A ONG informada não é válida");
        }

        return animalService.getAllOngAnimals(ong.getId());
    }

    private String getUsernameByToken() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
