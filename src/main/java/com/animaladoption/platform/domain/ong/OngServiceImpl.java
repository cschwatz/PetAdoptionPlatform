package com.animaladoption.platform.domain.ong;

import com.animaladoption.platform.infra.exceptions.ObjectNotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OngServiceImpl implements OngService {

    private OngRepository repository;

    public OngServiceImpl(OngRepository repository) {
        this.repository = repository;
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

        // TODO - Check if login/email are already taken
        // TODO - encryption of password after dealing with Spring security
        Ong ongEntity = new Ong(dto);
        Ong savedOng = repository.save(ongEntity);
        return new OngPostDTO(savedOng);
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

        if (!dto.password().isBlank()) {
            ongToUpdate.setPassword(dto.password());
        }

        if (!dto.email().isBlank()) {
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
}
