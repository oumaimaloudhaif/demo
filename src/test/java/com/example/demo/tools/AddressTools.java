package com.example.demo.tools;

import com.example.demo.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressTools {
  public static Address createAddress(Long id, String street, String city, String codePostal) {

    return new Address().withAddress_id(id).withStreet(street).withCity(city).withPostalCode(codePostal);
  }
}
