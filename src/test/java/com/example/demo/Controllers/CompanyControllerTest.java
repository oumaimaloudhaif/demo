package com.example.demo.Controllers;

import com.example.demo.Controllers.Request.CompanyRequest;
import com.example.demo.Controllers.Response.CompanyResponse;
import com.example.demo.Dto.CompanyDTO;
import com.example.demo.Entities.Company;
import com.example.demo.ServicesImpl.CompanyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CompanyControllerTest extends AbstractTest {

  @MockBean
  CompanyServiceImpl companyServiceImpl;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllCompaniesTestWhenCompanyExist() throws Exception {
    // Given
    final String uri = "/companies";
    final CompanyDTO companyDTO = new  CompanyDTO("company");
    final CompanyDTO companyDTO1 = new CompanyDTO("company");
    final List<CompanyDTO> listOfCompanies = List.of(companyDTO, companyDTO1);

    // When
    when(companyServiceImpl.getAllCompanies()).thenReturn(listOfCompanies);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    CompanyResponse companies = super.mapFromJson(content, CompanyResponse.class);
    assertEquals(2, companies.getResult().size());
  }

  @Test
  public void getAllCompaniesTestWhenNoCompanyExist() throws Exception {
    // Given
    final String uri = "/companies";
    final List<CompanyDTO> listOfCompanies = List.of();

    // When
    when(companyServiceImpl.getAllCompanies()).thenReturn(listOfCompanies);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    CompanyResponse companies= super.mapFromJson(content, CompanyResponse.class);
    assertEquals(0, companies.getResult().size());
  }

  @Test
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

  public void searchCompanyTestWhenKeywordIsNull() throws Exception {
    // Given
    final String uri = "/companies";
    // When
    when(companyServiceImpl.searchCompany(null)).thenReturn(List.of());
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
    CompanyDTO[] companies = super.mapFromJson(content, CompanyDTO[].class);
    assertEquals(0, companies.length);
  }

  @Test
  public void getCompanies_WithNonNullKeyword_ReturnsCompanies() throws Exception {
    // Given
    final String uri = "/companies";
    CompanyRequest companyRequest = new CompanyRequest();
    companyRequest.setKeyword("test");
    final CompanyDTO companyDTO = new CompanyDTO("company-Test");
    final CompanyDTO companyDTO1 = new CompanyDTO("company1-Test");
    final List<CompanyDTO> listOfCompanies= List.of(companyDTO, companyDTO1);
    CompanyResponse companyResponse = new CompanyResponse();
    companyResponse.setResult(listOfCompanies);

    // When
    when(companyServiceImpl.searchCompany(companyRequest.getKeyword()))
        .thenReturn(companyResponse.getResult());

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
    assertEquals(2, result.getResult().size());
  }

  @Test
  public void fetchCompanies_WithNullKeyword_ReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/companies";
    CompanyRequest companyRequest = new CompanyRequest();
    companyRequest.setKeyword("");
    final List<CompanyDTO> listOfCompanies = List.of();

    // When
    when(companyServiceImpl.getAllCompanies()).thenReturn(listOfCompanies);
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
    assertEquals(0, result.getResult().size());
  }

  @Test
  public void addCompanyTest() throws Exception {

    // Given
    final String uri = "/companies";
    Company company = new Company();
    company.setName("company");
    String inputJson = new ObjectMapper().writeValueAsString(company);
    final CompanyDTO companyDTO = new CompanyDTO("company");

    // When
    when(companyServiceImpl.addCompany(any(Company.class))).thenReturn(companyDTO);
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
    assertEquals(companyDTO.getName(), result.getName());
  }

  @Test
  public void updateCompany() throws Exception {
    // Given
    final String uri = "/companies";
    Company company = new Company();
    company.setName("company");
    String inputJson = new ObjectMapper().writeValueAsString(company);
    final CompanyDTO companyDTO = new CompanyDTO("company");

    // When
    when(companyServiceImpl.updateCompany(any(Company.class))).thenReturn(companyDTO);
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
    assertEquals(companyDTO.getName(), result.getName());
  }

}