package com.example.demo.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.AbstractTest;
import com.example.demo.controllers.request.AddressRequest;
import com.example.demo.controllers.request.CompanyRequest;
import com.example.demo.controllers.response.AddressResponse;
import com.example.demo.dto.AddressDTO;
import com.example.demo.entities.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;


class AddressUseCase extends AbstractTest {
  @Autowired
  private ObjectMapper objectMapper;
  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void fetchAllAddressesTest() throws Exception {
    // Given
    String url = "/addresses";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AddressResponse reports = super.mapFromJson(content, AddressResponse.class);
    Assertions.assertEquals(3, reports.getResult().size());
  }

  @Test
  public void getAllAddressTestWhenAddressExist() throws Exception {
    // Given
    final String uri = "/addresses";

    // When
    MvcResult mvcResult =
            mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AddressResponse addresses = super.mapFromJson(content, AddressResponse.class);
    assertEquals(3, addresses.getResult().size());
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
    assertEquals(4, address.getResult().size());
  }

  @Test
  public void getAddressWithNullKeywordInAddressRequest() throws Exception {
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

    // When
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
    assertEquals("city", result.getCity());
  }

  @Test
  public void updateAddress() throws Exception {
    // Given
    final String uri = "/addresses";
    Address address = new Address();
    address.setCity("city1");
    String inputJson = new ObjectMapper().writeValueAsString(address);

    // When
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
    assertEquals("city1", result.getCity());
  }

  @Test
  public void findAddressById() throws Exception {
    // Given
    final String uri = "/addresses/1";

    // When
    MvcResult mvcResult =
            mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AddressDTO result = objectMapper.readValue(content, AddressDTO.class);
    assertEquals("street", result.getStreet());
  }

  @Test
  public void deleteAddressNotExistTest() throws Exception {
    // Given
    String uri = "/addresses/30";

    // when
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    Boolean actualValue = Boolean.valueOf(content);
    assertEquals(false, actualValue);
  }

  @Test
  public void deleteAddressExistTest() throws Exception {
    // Given
    String uri = "/addresses/1";

    // when
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    Boolean actualValue = Boolean.valueOf(content);
    assertEquals(true, actualValue);
  }
}

