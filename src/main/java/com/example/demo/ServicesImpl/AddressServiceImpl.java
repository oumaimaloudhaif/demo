package com.example.demo.ServicesImpl;

import com.example.demo.Entities.Address;
import com.example.demo.Repository.AddressRepository;
import com.example.demo.Services.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Address Service Implementation
 */
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }
    public  List<Address> searchAddress(String keyword) {
        return addressRepository.findByCity(keyword);
    }

    public  Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public  Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

}
