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
  @Autowired private AddressRepository addressRepository;
  @Autowired private FromDOToDTO fromDOToDTO;

  /** @return List<AddressDTO> */
  @Override
  public List<AddressDTO> getAllAddresses() {
    final List<Address> addresses = addressRepository.findAll();
    List<AddressDTO> addressDTOS = new ArrayList<>();
    addresses.forEach(
        address -> {
          AddressDTO addressDTO = fromDOToDTO.mapAddress(address);
          addressDTOS.add(addressDTO);
        });

    return addressDTOS;
  }

  /**
   * @param keyword a keyword (city name) to search for addresses
   * @return List<AddressDTO>
   */
  @Override
  public List<AddressDTO> searchAddress(String keyword) {
    final List<Address> addresses = addressRepository.findByCity(keyword);
    List<AddressDTO> addressDTOS = new ArrayList<>();
    addresses.forEach(
        address -> {
          AddressDTO addressDTO = fromDOToDTO.mapAddress(address);
          addressDTOS.add(addressDTO);
        });
    return addressDTOS;
  }

  /**
   * @param address the address object to be added
   * @return AddressDTO
   */
  @Override
  public AddressDTO addAddress(Address address) {
    final Address savedAddress = addressRepository.save(address);
    return fromDOToDTO.mapAddress(savedAddress);
  }

  /**
   * @param address the address object to be updated
   * @return AddressDTO
   */
  @Override
  public AddressDTO updateAddress(Address address) {
    final Address updatedAddress = addressRepository.save(address);
    return fromDOToDTO.mapAddress(updatedAddress);
  }

  /**
   * Retrieves a address by its ID.
   *
   * @param addressId the ID of the address to retrieve
   * @return the AddressDTO corresponding to the address, or null if the address does not exist
   */
  @Override
  public AddressDTO getAddressById(Long addressId) {
    final Address address = addressRepository.findById(addressId).orElse(null);
    if (address != null) {

      return fromDOToDTO.mapAddress(address);
    } else {

      return null;
    }
  }

  /**
   * Deletes a company by its ID.
   *
   * @param addressId the ID of the address to delete
   * @return true if the address was deleted successfully, false otherwise
   */
  @Override
  public boolean deleteAddressById(Long addressId) {
    final Address address = addressRepository.findById(addressId).orElse(null);
    if (address != null) {
      addressRepository.delete(address);

      return true;
    } else {

      return false;
    }
  }
}
