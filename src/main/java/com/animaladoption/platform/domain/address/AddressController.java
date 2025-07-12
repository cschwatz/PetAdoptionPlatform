package com.animaladoption.platform.domain.address;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<AddressGetDTO> getAddressById(@PathVariable UUID id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AddressPostDTO> createNewAddress(@RequestBody @Valid AddressPostDTO dto) {
        return ResponseEntity.ok(addressService.createNewAddress(dto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AddressPutDTO> updateAddress(@PathVariable UUID id, @RequestBody AddressPutDTO dto) {
        return ResponseEntity.ok(addressService.updateAddress(id, dto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

}
