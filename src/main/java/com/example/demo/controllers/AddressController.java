package com.example.demo.controllers;

import com.example.demo.controllers.mappers.AddressMapper;
import com.example.demo.controllers.request.AddressRequest;
import com.example.demo.controllers.response.AddressResponse;
import com.example.demo.dto.AddressDTO;
import com.example.demo.entities.Address;
import com.example.demo.servicesImpl.AddressServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Address Controller */
@Validated
@RestController
public class AddressController {
  @Autowired private AddressServiceImpl addressServiceImpl;
  @Autowired private AddressMapper addressMapper;

  /**
   * Adds a new address
   *
   * @param address the address object to be added
   * @return AddressDTO
   */
  @PostMapping("/addresses")
  public AddressDTO addAddress(@RequestBody @Valid Address address) {
    return addressServiceImpl.addAddress(address);
  }

  /**
   * Updates an existing address
   *
   * @param address the address object to be updated
   * @return AddressDTO
   */
  @PutMapping("/addresses")
  public AddressDTO updateAddress(@RequestBody @Valid Address address) {
    return addressServiceImpl.updateAddress(address);
  }

  /**
   * @param addressRequest the request object containing the keyword related to the address
   * @return AddressResponse
   */
  @GetMapping("/addresses")
  public AddressResponse getAddresses(
      @RequestBody(required = false) @Valid AddressRequest addressRequest) {
    if (addressRequest != null && addressRequest.getKeyword() != null) {
      return addressMapper.toAddressResponse(
          addressServiceImpl.searchAddress(addressRequest.getKeyword()));
    } else {
      return addressMapper.toAddressResponse(addressServiceImpl.getAllAddresses());
    }
  }

  /**
   * Retrieves an address by its ID and deletes it.
   *
   * @param addressId the ID of the address to delete
   * @return true if the address was successfully deleted, false otherwise
   */
  @DeleteMapping("/addresses/{id}")
  public boolean deleteAddressById(@PathVariable("id") Long addressId) {

    return addressServiceImpl.deleteAddressById(addressId);
  }

  /**
   * Retrieves an address by its ID.
   *
   * @param addressId the ID of the address to retrieve
   * @return AddressDTO corresponding to the address, or null if the address does not exist
   */
  @GetMapping("/addresses/{id}")
  public AddressDTO getAddressById(@PathVariable("id") Long addressId) {

    return addressServiceImpl.getAddressById(addressId);
  }
}
