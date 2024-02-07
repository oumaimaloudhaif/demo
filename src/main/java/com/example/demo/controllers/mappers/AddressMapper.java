package com.example.demo.controllers.mappers;

import com.example.demo.controllers.response.AddressResponse;
import com.example.demo.dto.AddressDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/** Address Mapper */
@Component
public class AddressMapper {
  public AddressResponse toAddressResponse(List<AddressDTO> addresses) {
    AddressResponse addressResponse = new AddressResponse();
    addressResponse.setResult(addresses);

    return addressResponse;
  }
}
