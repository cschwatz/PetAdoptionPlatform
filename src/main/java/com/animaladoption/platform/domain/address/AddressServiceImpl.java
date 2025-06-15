package com.animaladoption.platform.domain.address;

import java.util.ArrayList;
import java.util.List;
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
}
