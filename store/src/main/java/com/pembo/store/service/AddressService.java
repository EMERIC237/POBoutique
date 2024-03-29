package com.pembo.store.service;

import com.pembo.store.dto.AddressDto;
import com.pembo.store.dto.UserDto;
import com.pembo.store.exception.ResourceNotFoundException;
import com.pembo.store.mapper.AddressMapper;
import com.pembo.store.model.Address;
import com.pembo.store.model.User;
import com.pembo.store.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    private final UserService userService;
    private final AddressMapper addressMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper, UserService userService) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.userService = userService;
    }

    /**
     * get all addresses
     *
     * @return List of all addresses
     */
    public List<AddressDto> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * get address by id
     *
     * @param id
     * @return address
     */
    public AddressDto getAddressById(Long id) {

        return addressMapper.toDto(findAddressById(id));
    }

    public List<AddressDto> getAddressesByUserId(long userId) {
        List<Address> userAddresses = addressRepository.findByUserId(userId);
        return userAddresses.stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * save address
     *
     * @param addressDto address receive from request.
     * @return saved address
     */
    public AddressDto saveAddress(AddressDto addressDto) {
        // Check if user exist first
        UserDto userDto = userService.getUserById(addressDto.userId()); // Exception will be thrown in userService if user doesn't exist

        Address address = addressMapper.toEntity(addressDto);
        Address savedAddress = addressRepository.save(address);
        return addressMapper.toDto(savedAddress);

    }

    public AddressDto updateAddress(Long id, AddressDto addressDto) {
        return addressRepository.findById(id)
                .map(address -> {
                    addressMapper.partialUpdate(addressDto, address);
                    return addressMapper.toDto(addressRepository.save(address));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Address with id " + id + " not " +
                        "found"));
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public Address findAddressById(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", addressId));
    }

    public List<Address> findAddressesByUserId(long userId) {
        return addressRepository.findByUserId(userId);
    }

    public Optional<Address> getAddressFromUserAddresses(long userId, long addressId) {
        Address currAddress = findAddressById(addressId);
        List<Address> userAddresses = findAddressesByUserId(userId);
        return userAddresses.stream().filter(address -> address.getId().equals(currAddress.getId())).findFirst();
    }

}
