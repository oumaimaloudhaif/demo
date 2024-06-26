package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.request.AddressRequest;
import com.example.demo.controllers.request.CompanyRequest;
import com.example.demo.controllers.response.AddressResponse;
import com.example.demo.dto.AddressDTO;
import com.example.demo.entities.Address;
import com.example.demo.servicesImpl.AddressServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
    AddressResponse addresses = super.mapFromJson(content, AddressResponse.class);
    assertEquals(0, addresses.getResult().size());
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

  @Test
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
    AddressResponse address = super.mapFromJson(content, AddressResponse.class);
    assertEquals(0, address.getResult().size());
  }

  @Test
  public void getAddressWithNullKeywordReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/addresses";
    AddressRequest addressRequest = new AddressRequest();
    addressRequest.setKeyword("");

    // When
    CompanyRequest companyRequest = new CompanyRequest();
    companyRequest.setKeyword("");
    mvc.perform(
            MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressRequest.getKeyword())))
        .andExpect(
            result ->
                assertInstanceOf(
                    MethodArgumentNotValidException.class, result.getResolvedException()))
        .andExpect(status().isBadRequest());
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
