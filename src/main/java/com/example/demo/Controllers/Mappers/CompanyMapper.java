package com.example.demo.Controllers.Mappers;

import com.example.demo.Controllers.Response.AddressResponse;
import com.example.demo.Entities.Address;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressMapper {
    public AddressResponse toAddressResponse(List<Address> addresses) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setResult(addresses);
        return addressResponse;
    }
}
