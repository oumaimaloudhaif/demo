package com.example.demo.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.AbstractTest;
import com.example.demo.controllers.request.EmployeeRequest;
import com.example.demo.controllers.response.FetchEmployeeResponse;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.enums.ContractType;
import com.example.demo.enums.Gender;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeesUseCase extends AbstractTest {
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  @Order(1)
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
    assertEquals(2, employees.getResult().size());
    assertEquals("Oumaima", employees.getResult().get(0).getName());
    assertEquals("Mayssa", employees.getResult().get(1).getName());
    assertEquals(50000, employees.getResult().get(0).getSalary());
    assertEquals(60000, employees.getResult().get(1).getSalary());
    assertEquals(Gender.FEMALE, employees.getResult().get(1).getGender());
    assertEquals(ContractType.CDI, employees.getResult().get(0).getContractType());
    assertEquals(ContractType.CDI, employees.getResult().get(1).getContractType());
  }

  @Test
  @Order(2)
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
  @Order(3)
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
    assertEquals(50000, employees.getResult().get(0).getSalary());
    assertEquals(ContractType.CDI, employees.getResult().get(0).getContractType());
  }

  @Test
  @Order(4)
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
    assertEquals("Oumaima", result.getResult().get(0).getName());
    assertEquals(50000, result.getResult().get(0).getSalary());
  }

  @Test
  @Order(5)
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
  @Order(6)
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
    FetchEmployeeResponse employees = objectMapper.readValue(content, FetchEmployeeResponse.class);
    assertEquals(3, employees.getResult().size());
    assertEquals("Oumaima", employees.getResult().get(0).getName());
    assertEquals(50000, employees.getResult().get(0).getSalary());
    assertEquals(ContractType.CDI, employees.getResult().get(0).getContractType());
  }

  @Test
  @Order(7)
  public void updateEmployee() throws Exception {
    // Given
    String uri = "/employees";
    Employee employee = new Employee();
    employee.setEmployee_id(1L);
    employee.setName("oumaima1");
    employee.setGender(Gender.FEMALE);
    employee.setSalary(5000);
    employee.setContractType(ContractType.CDD);
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
  @Order(8)
  public void deleteEmployeeNotExistTest() throws Exception {
    // Given
    String uri = "/employees/29";

    // when
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("Employee not found", content);
  }

  @Test
  @Order(9)
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
  @Order(10)
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
    assertEquals("oumaima1", result.getResult().get(0).getName());
    assertEquals(5000, result.getResult().get(0).getSalary());
  }

  @Test
  @Order(11)
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
    EmployeeDTO result = objectMapper.readValue(content, EmployeeDTO.class);
    assertEquals("oumaima1", result.getName());
    assertEquals(5000, result.getSalary());
    assertEquals(Gender.FEMALE, result.getGender());
    assertEquals(ContractType.CDD, result.getContractType());
  }
}
