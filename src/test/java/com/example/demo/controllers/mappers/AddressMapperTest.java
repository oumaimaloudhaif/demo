package com.example.demo.controllers.mappers;

import com.example.demo.controllers.response.AddressResponse;
import com.example.demo.dto.AddressDTO;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTest {
  @Autowired private AddressMapper addressMapper;

  @Test
  public void toAddressResponseTest() {
    // Given

    final AddressDTO addressDTO = new AddressDTO("street1", "city", "address");
    final AddressDTO addressDTO1 = new AddressDTO("street2", "city", "address");
    List<AddressDTO> AddressDTOs = List.of(addressDTO, addressDTO1);

    // When
    AddressResponse result = addressMapper.toAddressResponse(AddressDTOs);

    // Then
    Assert.assertEquals(result.getResult().size(), 2);
  }
}
