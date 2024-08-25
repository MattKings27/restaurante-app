package com.example.restaurante_app.services;

import com.example.restaurante_app.dtos.EditAddress;
import com.example.restaurante_app.dtos.NewAddress;
import com.example.restaurante_app.entities.Address;
import com.example.restaurante_app.exceptions.ResourceNotFoundException;
import com.example.restaurante_app.repositories.AddressRepository;
import com.example.restaurante_app.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // TODO: Find a proper way to do this and propagate to other services
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + id));
    }

    public Address createAddress(NewAddress newAddress) {
        var address = new Address();
        BeanUtils.copyProperties(newAddress, address);
        address.setUser(userRepository.getReferenceById(newAddress.userId()));
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, EditAddress editAddress) {
        Address address = addressRepository.getReferenceById(id);
        address.setAddress(editAddress.address());
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
