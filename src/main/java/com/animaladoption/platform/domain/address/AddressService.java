package com.animaladoption.platform.domain.address;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    public List<AddressGetDTO> findAllAddresses();

}
