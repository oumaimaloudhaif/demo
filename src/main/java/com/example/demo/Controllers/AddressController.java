package com.example.demo.Controllers;

import com.example.demo.Controllers.Mappers.AddressMapper;
import com.example.demo.Controllers.Request.AddressRequest;
import com.example.demo.Controllers.Response.AddressResponse;
import com.example.demo.Dto.AddressDTO;
import com.example.demo.Entities.Address;
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

  /** @return addresses */
  /*  @GetMapping("/alladdresses")
  public AddressResponse getAllAddresses() {

      return addressMapper.toAddressResponse(addressServiceImpl.getAllAddresses());
  }*/
  @PostMapping("/addresses")
  public AddressDTO addAddress(@RequestBody @Valid Address address) {
    return addressServiceImpl.addAddress(address);
  }
  /** @return Address */
  @PutMapping("/addresses")
  public AddressDTO updateAddress(@RequestBody @Valid Address address) {
    return addressServiceImpl.updateAddress(address);
  }
  /** @return AddressResponse */
  /*  @GetMapping("/addresses")
  public AddressResponse searchAddress(@RequestParam(required =false) @Valid AddressRequest addressRequest) {
      return addressMapper.toAddressResponse(addressServiceImpl.searchAddress(addressRequest.getKeyword()));
  }*/
  @GetMapping("/addresses")
  public AddressResponse getAddresses(
      @RequestParam(required = false) @Valid AddressRequest addressRequest) {
    if (addressRequest != null && addressRequest.getKeyword() != null) {
      return addressMapper.toAddressResponse(
          addressServiceImpl.searchAddress(addressRequest.getKeyword()));
    } else {
      return addressMapper.toAddressResponse(addressServiceImpl.getAllAddresses());
    }
  }
}
