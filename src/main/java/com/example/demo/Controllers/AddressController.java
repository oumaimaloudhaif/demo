package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.AddressMapper;
import com.example.demo.Controllers.Request.AddressRequest;
import com.example.demo.Controllers.Response.AddressResponse;
import com.example.demo.dto.AddressDTO;
import com.example.demo.entities.Address;
import com.example.demo.ServicesImpl.AddressServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class AddressController {
  @Autowired private AddressServiceImpl addressServiceImpl;
  @Autowired private AddressMapper addressMapper;

  /**
   *
   * @param address
   * @return AddressDTO
   */
  @PostMapping("/addresses")
  public AddressDTO addAddress(@RequestBody @Valid Address address) {
    return addressServiceImpl.addAddress(address);
  }

  /**
   *
   * @param address
   * @return AddressDTO
   */
  @PutMapping("/addresses")
  public AddressDTO updateAddress(@RequestBody @Valid Address address) {
    return addressServiceImpl.updateAddress(address);
  }

  /**
   *
   * @param addressRequest
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
}
