package com.example.demo.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.AbstractTest;
import com.example.demo.controllers.request.AddressRequest;
import com.example.demo.controllers.response.AddressResponse;
import com.example.demo.dto.AddressDTO;
import com.example.demo.entities.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressUseCase extends AbstractTest {
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  @Order(1)
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
    assertEquals(2, reports.getResult().size());
  }

  @Test
  @Order(2)
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
  @Order(3)
  public void addAddressTest() throws Exception {

    // Given
    final String uri = "/addresses";
    Address address = new Address();
    address.setCity("city");
    address.setPostalCode("7034");
    address.setStreet("street");
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
    assertEquals(address.getCity(), result.getCity());
    assertEquals(address.getStreet(), result.getStreet());
    assertEquals(address.getPostalCode(), result.getPostalCode());
  }
  @Test
  @Order(4)
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
  @Order(5)
  public void getAddressWithNullKeywordInAddressRequest() throws Exception {
    // Given
    final String uri = "/addresses";
    AddressRequest addressRequest = new AddressRequest();
    addressRequest.setKeyword("");

    // When
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
  @Order(6)
  public void updateAddressExist() throws Exception {
    // Given
    final String uri = "/addresses";
    Address address = new Address();
    address.setAddress_id(1L);
    address.setCity("city1");
    address.setPostalCode("7034");
    address.setStreet("street1");
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
    assertEquals(address.getCity(), result.getCity());
    assertEquals(address.getStreet(), result.getStreet());
    assertEquals(address.getPostalCode(), result.getPostalCode());
  }

  @Test
  @Order(7)
  public void updateNotExistAddress() throws Exception {
    // Given
    final String uri = "/addresses";
    Address address = new Address();
    address.setCity("city1");
    address.setPostalCode("7034");
    address.setStreet("street1");
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
    assertEquals(address.getCity(), result.getCity());
    assertEquals(address.getStreet(), result.getStreet());
    assertEquals(address.getPostalCode(), result.getPostalCode());
  }

  @Test
  @Order(8)
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
    assertEquals("street1", result.getStreet());
    assertEquals("city1", result.getCity());
    assertEquals("7034", result.getPostalCode());
  }

  @Test
  @Order(9)
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
  @Order(10)
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

  @Test
  @Order(11)
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
}
