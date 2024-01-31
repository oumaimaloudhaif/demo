package com.example.demo.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.Controllers.Request.AddressRequest;
import com.example.demo.Controllers.Response.AddressResponse;
import com.example.demo.Dto.AddressDTO;
import com.example.demo.Entities.Address;
import com.example.demo.ServicesImpl.AddressServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class AddressControllerTest extends AbstractTest {

  @MockBean AddressServiceImpl addressServiceImpl;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllAddressTestWhenAddressExist() throws Exception {
    // Given
    final String uri = "/addresses";
    final AddressDTO addressDTO = new AddressDTO("street1", "city", "address");
    final AddressDTO addressDTO1 = new AddressDTO("street1", "city", "address");
    final List<AddressDTO> listOfAddress = List.of(addressDTO, addressDTO1);

    // When
    when(addressServiceImpl.getAllAddresses()).thenReturn(listOfAddress);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AddressResponse addresses = super.mapFromJson(content, AddressResponse.class);
    assertEquals(2, addresses.getResult().size());
  }

  @Test
  public void getAllAddressTestWhenNoAddressExist() throws Exception {
    // Given
    final String uri = "/addresses";
    final List<AddressDTO> listOfAddress = List.of();

    // When
    when(addressServiceImpl.getAllAddresses()).thenReturn(listOfAddress);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AddressResponse addresss = super.mapFromJson(content, AddressResponse.class);
    assertEquals(0, addresss.getResult().size());
  }

  @Test
  public void getAllAddressTestWrongPath() throws Exception {
    // Given
    final String uri = "/addressess";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }

  public void searchAddressTestWhenKeywordIsNull() throws Exception {
    // Given
    final String uri = "/addresses";
    // When
    when(addressServiceImpl.searchAddress(null)).thenReturn(List.of());
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .param("keyword", (String) null)
                    .accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AddressDTO[] address = super.mapFromJson(content, AddressDTO[].class);
    assertEquals(0, address.length);
  }

  @Test
  public void getAddress_WithNullKeyword_ReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/addresses";
    AddressRequest addressRequest = new AddressRequest();
    addressRequest.setKeyword("");
    final List<AddressDTO> listOfAddress = List.of();

    // When
    when(addressServiceImpl.getAllAddresses()).thenReturn(listOfAddress);
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(addressRequest.getKeyword())))
            .andExpect(status().isOk())
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AddressResponse result = objectMapper.readValue(content, AddressResponse.class);
    assertEquals(0, result.getResult().size());
  }

  @Test
  public void addAddressTest() throws Exception {

    // Given
    final String uri = "/addresses";
    Address address = new Address();
    address.setCity("city");
    String inputJson = new ObjectMapper().writeValueAsString(address);
    final AddressDTO addressDTO = new AddressDTO("street", "city", "address");

    // When
    when(addressServiceImpl.addAddress(any(Address.class))).thenReturn(addressDTO);
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AddressDTO result = objectMapper.readValue(content, AddressDTO.class);
    assertEquals(addressDTO.getCity(), result.getCity());
  }

  @Test
  public void updateAddress() throws Exception {
    // Given
    final String uri = "/addresses";
    Address address = new Address();
    address.setCity("address");
    String inputJson = new ObjectMapper().writeValueAsString(address);
    final AddressDTO addressDTO = new AddressDTO("street", "city", "address");

    // When
    when(addressServiceImpl.updateAddress(any(Address.class))).thenReturn(addressDTO);
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.put(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AddressDTO result = objectMapper.readValue(content, AddressDTO.class);
    assertEquals(addressDTO.getCity(), result.getCity());
  }
}
