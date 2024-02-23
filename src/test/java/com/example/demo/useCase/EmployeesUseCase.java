package com.example.demo.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.AbstractTest;
import com.example.demo.controllers.request.EmployeeRequest;
import com.example.demo.controllers.response.FetchEmployeeResponse;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entities.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;;

class EmployeesUseCase extends AbstractTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void fetchAllEmployeesTest() throws Exception {
    // Given
    String url = "/employees";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    FetchEmployeeResponse employees = objectMapper.readValue(content, FetchEmployeeResponse.class);
    Assertions.assertEquals(2, employees.getResult().size());
  }

  @Test
  public void getAllEmployeesTestWrongPath() throws Exception {
    // Given
    final String uri = "/emloyees";

    // when
    MvcResult mvcResult =
            mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }

  @Test
  public void searchEmployeeTestWhenKeyWordNotNull() throws Exception {
    // given
    final String uri = "/employees";
    final String keyword = "Oumaima";
    // when

    MvcResult mvcResult =
            mvc.perform(
                            MockMvcRequestBuilders.get(uri)
                                    .param("keyword", keyword)
                                    .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    FetchEmployeeResponse employees = objectMapper.readValue(content, FetchEmployeeResponse.class);
    assertEquals("Oumaima", employees.getResult().get(0).getName());
  }

  @Test
  public void searchEmployeeTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/employees";
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
    FetchEmployeeResponse result = objectMapper.readValue(content, FetchEmployeeResponse.class);
    assertEquals(2, result.getResult().size());
  }

  @Test
  public void fetchEmployeesWithNonNullKeywordReturnsEmployees() throws Exception {
    // given
    final String uri = "/employees";
    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setKeyword("Oumaima");

    // when
      MvcResult mvcResult =
            mvc.perform(
                            MockMvcRequestBuilders.get(uri)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(employeeRequest.getKeyword())))
                    .andExpect(status().isOk())
                    .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    FetchEmployeeResponse result = objectMapper.readValue(content, FetchEmployeeResponse.class);
    assertEquals(1, result.getResult().size());
  }

  @Test
  public void fetchEmployees_WithNullKeyword_ReturnsNonEmptyList() throws Exception {
    // Given
    final String uri = "/employees";
    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setKeyword("");
    // When
    MvcResult mvcResult =
            mvc.perform(
                            MockMvcRequestBuilders.get(uri)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(employeeRequest.getKeyword())))
                    .andExpect(status().isOk())
                    .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    FetchEmployeeResponse result = objectMapper.readValue(content, FetchEmployeeResponse.class);
    assertEquals(2, result.getResult().size());
  }

  @Test
  public void addEmployeeTest() throws Exception {

    // Given
    final String uri = "/employees";
    Employee employee = new Employee();
    employee.setName("oumaima");
    employee.setSalary(50000);
    String inputJson = new ObjectMapper().writeValueAsString(employee);

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
    assertEquals("Employee added successfully", content);
  }

  @Test
  public void updateEmployee() throws Exception {
    // Given
    String uri = "/employees";
    Employee employee = new Employee();
    employee.setEmployee_id(1L);
    employee.setName("oumaima1");
    employee.setSalary(5000);
    String inputJson = new ObjectMapper().writeValueAsString(employee);

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
    assertEquals("Employee is updated successfully", content);
  }

  @Test
  public void deleteEmployeeNotExistTest() throws Exception {
    // Given
    String uri = "/employees/29";

    // when
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("Employee not deleted successfully", content);
  }

  @Test
  public void deleteEmployeeExistTest() throws Exception {
    // Given
    String uri = "/employees/2";

    // when
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("Employee is deleted successfully", content);
  }

  @Test
  public void findEmployeeById() throws Exception {
    // Given
    final String uri = "/employees/1";

    // When
    MvcResult mvcResult =
            mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                    .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    DepartmentDTO result = objectMapper.readValue(content, DepartmentDTO.class);
    assertEquals("Oumaima", result.getName());
  }
}
