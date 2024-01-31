package com.example.demo.ServicesImpl;

import com.example.demo.Dto.AddressDTO;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Entities.Address;
import com.example.demo.Repository.AddressRepository;
import com.example.demo.Services.AddressService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Address Service Implementation */
@Service
public class AddressServiceImpl implements AddressService {
  @Autowired private AddressRepository addressRepository;
  @Autowired private FromDOToDTO fromDOToDTO;

  public List<AddressDTO> getAllAddresses() {
    final List<Address> addresses = addressRepository.findAll();
    List<AddressDTO> addressDTOS = new ArrayList<>();
    addresses.forEach(
        address -> {
          AddressDTO addressDTO = fromDOToDTO.MapAdress(address);
          addressDTOS.add(addressDTO);
        });
    return addressDTOS;
  }

  public List<AddressDTO> searchAddress(String keyword) {
    final List<Address> addresses = addressRepository.findByCity(keyword);
    List<AddressDTO> addressDTOS = new ArrayList<>();
    addresses.forEach(
        address -> {
          AddressDTO addressDTO = fromDOToDTO.MapAdress(address);
          addressDTOS.add(addressDTO);
        });
    return addressDTOS;
  }

  public AddressDTO addAddress(Address address) {
    final Address savedAddress = addressRepository.save(address);
    return fromDOToDTO.MapAdress(savedAddress);
  }

  public AddressDTO updateAddress(Address address) {
    final Address updatedAddress = addressRepository.save(address);
    return fromDOToDTO.MapAdress(updatedAddress);
  }
}
