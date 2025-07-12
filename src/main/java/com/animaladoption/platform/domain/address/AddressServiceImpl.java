package com.animaladoption.platform.domain.address;

import com.animaladoption.platform.exceptions.ObjectNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class AddressServiceImpl implements AddressService {

    private AddressRepository repository;

    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AddressGetDTO> findAllAddresses() {
        List<Address> returnedAddresses = repository.findAll();
        return returnedAddresses
                .stream()
                .map(AddressGetDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public AddressGetDTO getAddressById(UUID id) {
        Optional<Address> addressOpt = repository.findById(id);

        if (addressOpt.isEmpty()) {
            throw new ObjectNotFound("Não foi possível encontrar o endereço");
        }

        return new AddressGetDTO(addressOpt.get());
    }

    @Override
    public AddressPostDTO createNewAddress(AddressPostDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O Endereço informado não é válido");
        }

        Address addressEntity = new Address(dto);
        Address savedAddress = repository.save(addressEntity);
        return new AddressPostDTO(savedAddress);
    }

    @Override
    public AddressPutDTO updateAddress(UUID id, AddressPutDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O DTO do endereço é inválido");
        }

        Optional<Address> addressOpt = repository.findById(id);
        if (addressOpt.isEmpty()) {
            throw new ObjectNotFound("O endereço informado não foi encontrado");
        }

        Address addressToUpdate = addressOpt.get();
        if (!dto.state().isBlank()) {
            addressToUpdate.setState(dto.state());
        }

        if (!dto.city().isBlank()) {
            addressToUpdate.setCity(dto.city());
        }

        if (!dto.neighborhood().isBlank()) {
            addressToUpdate.setNeighborhood(dto.neighborhood());
        }

        if (!dto.street().isBlank()) {
            addressToUpdate.setStreet(dto.street());
        }

        if (dto.num() != null) {
            addressToUpdate.setNumber(dto.num());
        }

        if (!dto.cep().isBlank()) {
            addressToUpdate.setCep(dto.cep());
        }

        Address updatedAddress = repository.save(addressToUpdate);
        return new AddressPutDTO(updatedAddress);
    }

    @Override
    public void deleteAddress(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Endereço informado inválido");
        }

        Optional<Address> addressOpt = repository.findById(id);
        if (addressOpt.isEmpty()) {
            throw new ObjectNotFound("Endereço não encontrado");
        }

        repository.delete(addressOpt.get());
    }
}
