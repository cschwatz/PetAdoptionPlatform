package com.animaladoption.platform.domain.address;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AddressService {

    public List<AddressGetDTO> findAllAddresses();

    public AddressGetDTO getAddressById(UUID id);

    public AddressPostDTO createNewAddress(AddressPostDTO dto);

    public AddressPutDTO updateAddress(UUID id, AddressPutDTO dto);

    public void deleteAddress(UUID id);
}
