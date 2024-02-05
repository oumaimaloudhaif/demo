package com.example.demo.tools;

import com.example.demo.dto.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressDTOTools {
  public static AddressDTO createAddressDTO(String street, String city, String codePostal) {

    return new AddressDTO().withStreet(street).withCity(city).withPostalCode(codePostal);
  }
}
