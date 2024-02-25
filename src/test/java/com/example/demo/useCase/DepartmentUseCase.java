package com.example.demo.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.AbstractTest;
import com.example.demo.controllers.request.DepartmentRequest;
import com.example.demo.controllers.response.DepartmentResponse;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entities.Department;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class DepartmentUseCase extends AbstractTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void fetchAllDepartmentsTest() throws Exception {
    // Given
    String url = "/departments";

    // When
    MvcResult mvcResult =
            mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    DepartmentResponse reports = super.mapFromJson(content, DepartmentResponse.class);
    Assertions.assertEquals(2, reports.getResult().size());
  }

  @Test
  public void getAllDepartmentsTestWrongPath() throws Exception {
    // given
    final String uri = "/departmentss";

    // when
    MvcResult mvcResult =
            mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }

  @Test
  public void searchDepartmentTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/departments";

    // when
    MvcResult mvcResult =
            mvc.perform(
                            MockMvcRequestBuilders.get(uri)
                                    .param("keyword", (String) null)
                                    .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    DepartmentResponse departments = super.mapFromJson(content, DepartmentResponse.class);
    assertEquals(3, departments.getResult().size());
  }

  @Test
  public void addDepartmentTest() throws Exception {

    // Given
    final String uri = "/departments";
    Department department = new Department();
    department.setName("Engineering1");
    String inputJson = new ObjectMapper().writeValueAsString(department);
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
    DepartmentDTO result = objectMapper.readValue(content, DepartmentDTO.class);
    assertEquals("Engineering1", result.getName());
  }

  @Test
  public void updateDepartment() throws Exception {
    // Given
    final String uri = "/departments";
    Department department = new Department();
    department.setName("department1");
    String inputJson = new ObjectMapper().writeValueAsString(department);

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
    DepartmentDTO result = objectMapper.readValue(content, DepartmentDTO.class);
    assertEquals("department1", result.getName());
  }

  @Test
  public void fetchDepartmentsWithNullKeywordReturnsNonEmptyList() throws Exception {
    // Given
    final String uri = "/departments";
    DepartmentRequest departmentRequest = new DepartmentRequest();
    departmentRequest.setKeyword("");

    // When
    MvcResult mvcResult =
            mvc.perform(
                            MockMvcRequestBuilders.get(uri)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(departmentRequest.getKeyword())))
                    .andExpect(status().isOk())
                    .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    DepartmentResponse result = objectMapper.readValue(content, DepartmentResponse.class);
    assertEquals(3, result.getResult().size());
  }

  @Test
  public void findDepartmentById() throws Exception {
    // Given
    final String uri = "/departments/1";

    // When
    MvcResult mvcResult =
            mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    DepartmentDTO result = objectMapper.readValue(content, DepartmentDTO.class);
    assertEquals("Engineering", result.getName());
  }

  @Test
  public void deleteDepartmentNotExistTest() throws Exception {
    // Given
    String uri = "/departments/30";

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
  public void deleteDepartmentExistTest() throws Exception {
    // Given
    String uri = "/departments/1";

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