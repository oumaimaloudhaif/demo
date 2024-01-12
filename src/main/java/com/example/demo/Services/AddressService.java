package com.example.demo.Services;

import com.example.demo.Entities.Address;

import java.util.List;

/**
 * AddressService
 */
public interface AddressService {
    List<Address> getAllAddresses();
    List<Address> searchAddress(String keyword);
   Address addAddress(Address address);
   Address updateAddress(Address address);

}
