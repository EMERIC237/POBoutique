package com.pembo.store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.pembo.store.dto.AddressDto;
import com.pembo.store.service.AddressService;

@RestController
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("api/v1/addresses")
    public List<AddressDto> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("api/v1/addresses/{id}")
    public AddressDto getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @GetMapping("api/v1/users/{userId}/addresses")
    public List<AddressDto> getAllAddressesByUserId(@PathVariable long userId) {
        return addressService.getAddressesByUserId(userId);
    }

    @PostMapping("api/v1/users/{userId}/addresses")
    public AddressDto createAddress(@RequestBody AddressDto addressDto) {
        return addressService.saveAddress(addressDto);
    }

    @PutMapping("api/v1/addresses/{id}")
    public AddressDto updateAddress(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        return addressService.updateAddress(id, addressDto);
    }

    @DeleteMapping("api/v1/addresses/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
