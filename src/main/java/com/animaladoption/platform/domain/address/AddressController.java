package com.animaladoption.platform.domain.address;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService service) {
        this.addressService = service;
    }

    @GetMapping
    public ResponseEntity<List<AddressGetDTO>> getAllAddresses() {
        return ResponseEntity.ok(addressService.findAllAddresses());
    }

}
