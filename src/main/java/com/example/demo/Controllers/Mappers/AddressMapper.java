package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.AddressResponse;
import com.example.demo.Dto.AddressDTO;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
  public AddressResponse toAddressResponse(List<AddressDTO> addresses) {
    AddressResponse addressResponse = new AddressResponse();
    addressResponse.setResult(addresses);
    return addressResponse;
  }
}
