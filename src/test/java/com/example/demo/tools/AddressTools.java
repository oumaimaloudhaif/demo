package com.example.demo.tools;

import com.example.demo.Entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressTools {
  public static Address createAddress(Long id, String street, String city, String codePostal) {
    return new Address()
        .withAddress_id(id)
        .withStreet(street)
        .withCity(city)
        .withPostalCode(codePostal);
  }

  public static Address createAddress() {
    return new Address()
        .withAddress_id(1L)
        .withStreet("street")
        .withCity("city")
        .withPostalCode("codePostal");
  }
}
