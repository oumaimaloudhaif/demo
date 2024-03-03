package com.example.demo.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.AbstractTest;
import com.example.demo.controllers.request.CompanyRequest;
import com.example.demo.controllers.response.CompanyResponse;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.entities.Company;
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
class CompanyUseCase extends AbstractTest {
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  @Order(1)
  public void fetchAllCompaniesTest() throws Exception {
    // Given
    String url = "/companies";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    CompanyResponse reports = super.mapFromJson(content, CompanyResponse.class);
    assertEquals(2, reports.getResult().size());
  }

  @Test
  @Order(2)
  public void getAllCompaniesTestWrongPath() throws Exception {
    // Given
    final String uri = "/companiess";

    // when
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }


  @Test
  @Order(3)
  public void getCompaniesWithNonNullKeywordReturnsCompanies() throws Exception {
    // Given
    final String uri = "/companies";
    CompanyRequest companyRequest = new CompanyRequest();
    companyRequest.setKeyword("Company");

    // When
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(companyRequest.getKeyword())))
            .andExpect(status().isOk())
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    CompanyResponse result = objectMapper.readValue(content, CompanyResponse.class);
    assertEquals(1, result.getResult().size());
  }

  @Test
  @Order(4)
  public void fetchCompaniesWithNonNullKeywordReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/companies";
    CompanyRequest companyRequest = new CompanyRequest();
    companyRequest.setKeyword("t");

    // When
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(companyRequest.getKeyword())))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    CompanyResponse result = objectMapper.readValue(content, CompanyResponse.class);
    assertEquals(0, result.getResult().size());
  }

  @Test
  @Order(5)
  public void fetchCompaniesWithNullKeywordThrowException() throws Exception {
    // Given
    final String uri = "/companies";
    CompanyRequest companyRequest = new CompanyRequest();
    companyRequest.setKeyword("");
    mvc.perform(
            MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyRequest.getKeyword())))
        .andExpect(
            result ->
                assertInstanceOf(
                    MethodArgumentNotValidException.class, result.getResolvedException()))
        .andExpect(status().isBadRequest());
  }

  @Test
  @Order(6)
  public void addCompanyTest() throws Exception {

    // Given
    final String uri = "/companies";
    Company company = new Company();
    company.setName("company");
    String inputJson = new ObjectMapper().writeValueAsString(company);

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
    CompanyDTO result = objectMapper.readValue(content, CompanyDTO.class);
    assertEquals("company", result.getName());
  }

  @Test
  @Order(7)
  public void searchCompanyTestWhenKeywordIsNull() throws Exception {
    // Given
    final String uri = "/companies";

    // When
    MvcResult mvcResult =
            mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    CompanyResponse companies = super.mapFromJson(content, CompanyResponse.class);
    assertEquals(3, companies.getResult().size());
  }

  @Test
  @Order(8)
  public void updateCompany() throws Exception {
    // Given
    final String uri = "/companies";
    Company company = new Company();
    company.setCompany_id(1L);
    company.setName("newCompany");
    String inputJson = new ObjectMapper().writeValueAsString(company);

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
    CompanyDTO result = objectMapper.readValue(content, CompanyDTO.class);
    assertEquals("newCompany", result.getName());
  }

  @Test
  @Order(9)
  public void findCompaniesById() throws Exception {
    // Given
    final String uri = "/companies/1";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    CompanyDTO result = objectMapper.readValue(content, CompanyDTO.class);
    assertEquals("newCompany", result.getName());
  }

  @Test
  @Order(10)
  public void deleteCompanyNotExistTest() throws Exception {
    // Given
    String uri = "/companies/30";

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
  @Order(11)
  public void deleteCompanyExistTest() throws Exception {
    // Given
    String uri = "/companies/1";

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
  @Order(12)
  public void fetchCompaniesWithNullKeywordReturnsNonEmptyList() throws Exception {
    // Given
    final String uri = "/companies";
    CompanyRequest companyRequest = new CompanyRequest();
    companyRequest.setKeyword("");

    // When
    mvc.perform(
                    MockMvcRequestBuilders.get(uri)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(companyRequest.getKeyword())))
            .andExpect(
                    result ->
                            assertInstanceOf(
                                    MethodArgumentNotValidException.class, result.getResolvedException()))
            .andExpect(status().isBadRequest());
  }
}
