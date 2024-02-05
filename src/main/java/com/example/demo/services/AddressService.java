package com.example.demo.services;

import com.example.demo.dto.AddressDTO;
import com.example.demo.entities.Address;
import java.util.List;

/** AddressService */
public interface AddressService {
  List<AddressDTO> getAllAddresses();

  List<AddressDTO> searchAddress(String keyword);

  AddressDTO addAddress(Address address);

  AddressDTO updateAddress(Address address);
}
