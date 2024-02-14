package com.example.demo.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.tools.AddressDTOTools;
import com.example.demo.tools.AddressTools;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = DemoApplication.class)
@AutoConfigureMockMvc
public class AddressServiceImplTest {
  @MockBean private AddressRepository addressRepository;
  @Autowired private AddressServiceImpl addressService;
  @MockBean private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllAddress() {
    // Given
    final Address address1 = AddressTools.createAddress(1L, "street", "city", "codePostal");
    final Address address2 = AddressTools.createAddress(2L, "street1", "city1", "codePostal1");
    final AddressDTO address1DTO = AddressDTOTools.createAddressDTO("street", "city", "codePostal");
    final AddressDTO address2DTO =
        AddressDTOTools.createAddressDTO("street1", "city1", "codePostal1");

    final List<Address> mockedAddress = Arrays.asList(address1, address2);

    // When
    when(addressRepository.findAll()).thenReturn(mockedAddress);
    when(fromDOToDTO.MapAddress(address1)).thenReturn(address1DTO);
    when(fromDOToDTO.MapAddress(address1)).thenReturn(address2DTO);
    final List<AddressDTO> address = addressService.getAllAddresses();

    // Then
    assertEquals(mockedAddress.size(), address.size());
  }

  @Test
  public void testSearchAddress() {
    // Given
    final String keyword = "Oumaima";
    final Address address1 = AddressTools.createAddress(1L, "street", "city", "codePostal");
    final Address address2 = AddressTools.createAddress(2L, "street1", "city1", "codePostal1");
    final AddressDTO address1DTO = AddressDTOTools.createAddressDTO("street", "city", "codePostal");
    final AddressDTO address2DTO =
        AddressDTOTools.createAddressDTO("street1", "city1", "codePostal1");
    final List<Address> mockedAddress = Arrays.asList(address1, address2);

    // When
    when(addressRepository.findByCity(keyword)).thenReturn(mockedAddress);
    when(fromDOToDTO.MapAddress(address1)).thenReturn(address1DTO);
    when(fromDOToDTO.MapAddress(address1)).thenReturn(address2DTO);
    final List<AddressDTO> address = addressService.searchAddress(keyword);

    // Then
    assertEquals(mockedAddress.size(), address.size());
  }

  @Test
  public void testAddAddress() {
    // Given
    final Address inputAddress = AddressTools.createAddress(1L, "street", "city", "codePostal");
    final AddressDTO expectedAddressDTO =
        AddressDTOTools.createAddressDTO("street", "city", "codePostal");

    // When
    when(addressRepository.save(inputAddress)).thenReturn(inputAddress);
    when(fromDOToDTO.MapAddress(inputAddress)).thenReturn(expectedAddressDTO);
    final AddressDTO resultAddressDTO = addressService.addAddress(inputAddress);

    // Then
    assertEquals(expectedAddressDTO.getPostalCode(), resultAddressDTO.getPostalCode());
    assertEquals(expectedAddressDTO.getCity(), resultAddressDTO.getCity());
    assertEquals(expectedAddressDTO.getStreet(), resultAddressDTO.getStreet());
  }

  @Test
  public void testUpdateAddress() {
    // Given
    final Address inputAddress = AddressTools.createAddress(1L, "street", "city", "codePostal");
    final AddressDTO expectedAddressDTO =
        AddressDTOTools.createAddressDTO("street", "city", "codePostal");

    // When
    when(addressRepository.save(inputAddress)).thenReturn(inputAddress);
    when(fromDOToDTO.MapAddress(inputAddress)).thenReturn(expectedAddressDTO);
    final AddressDTO resultAddressDTO = addressService.addAddress(inputAddress);

    // Then
    assertEquals(expectedAddressDTO.getPostalCode(), resultAddressDTO.getPostalCode());
    assertEquals(expectedAddressDTO.getCity(), resultAddressDTO.getCity());
    assertEquals(expectedAddressDTO.getStreet(), resultAddressDTO.getStreet());
  }
}
