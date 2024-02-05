package com.example.demo.servicesImpl;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.services.AddressService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Address Service Implementation */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private FromDOToDTO fromDOToDTO;

    /**
     * @return List<AddressDTO>
     */
    public List<AddressDTO> getAllAddresses() {
        final List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> addressDTOS = new ArrayList<>();
        addresses.forEach(
                address -> {
                    AddressDTO addressDTO = fromDOToDTO.MapAddress(address);
                    addressDTOS.add(addressDTO);
                });

        return addressDTOS;
    }

    /**
     * @param keyword
     * @return List<AddressDTO>
     */
    public List<AddressDTO> searchAddress(String keyword) {
        final List<Address> addresses = addressRepository.findByCity(keyword);
        List<AddressDTO> addressDTOS = new ArrayList<>();
        addresses.forEach(
                address -> {
                    AddressDTO addressDTO = fromDOToDTO.MapAddress(address);
                    addressDTOS.add(addressDTO);
                });
        return addressDTOS;
    }

    /**
     * @param address
     * @return AddressDTO
     */

    public AddressDTO addAddress(Address address) {
        final Address savedAddress = addressRepository.save(address);
        return fromDOToDTO.MapAddress(savedAddress);
    }

    /**
     * @param address
     * @return AddressDTO
     */
    public AddressDTO updateAddress(Address address) {
        final Address updatedAddress = addressRepository.save(address);
        return fromDOToDTO.MapAddress(updatedAddress);
    }
}
