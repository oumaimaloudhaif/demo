package com.example.demo.tools;

import com.example.demo.Dto.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressDTOTools {
  public static AddressDTO createAddressDTO(String street, String city, String codePostal) {
    return new AddressDTO(street,city,codePostal);
  }
}
