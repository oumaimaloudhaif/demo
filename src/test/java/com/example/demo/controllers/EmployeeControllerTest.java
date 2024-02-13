package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.request.EmployeeRequest;
import com.example.demo.controllers.response.FetchEmployeeResponse;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.enums.ContractType;
import com.example.demo.enums.Gender;
import com.example.demo.exceptions.InternalException;
import com.example.demo.servicesImpl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class EmployeeControllerTest extends AbstractTest {

  @MockBean EmployeeServiceImpl employeeServiceImpl;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllEmployeesTestWhenEmployeeExist() throws Exception {
    // Given
    final String uri = "/employees";
    final EmployeeDTO employee = new EmployeeDTO("oma", 5000, Gender.FEMALE, ContractType.CDI);
    final EmployeeDTO employee2 = new EmployeeDTO("oma1", 2000, Gender.FEMALE, ContractType.CDI);
    final List<EmployeeDTO> listOfEmployees = List.of(employee, employee2);

    // When
    when(employeeServiceImpl.getAllEmployees()).thenReturn(listOfEmployees);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    FetchEmployeeResponse employees = super.mapFromJson(content, FetchEmployeeResponse.class);
    assertEquals(2, employees.getResult().size());
  }

  @Test
  public void getAllEmployeesTestWhenNoEmployeeExist() throws Exception {
    // Given
    final String uri = "/employees";
    final List<EmployeeDTO> listOfEmployees = List.of();

    // When
    when(employeeServiceImpl.getAllEmployees()).thenReturn(listOfEmployees);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    FetchEmployeeResponse employees = super.mapFromJson(content, FetchEmployeeResponse.class);
    assertEquals(0, employees.getResult().size());
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
  public void getAllEmployeesTestThenThrowException() throws Exception {
    // Given
    final String uri = "/employees";

    // When
    when(employeeServiceImpl.getAllEmployees())
        .thenThrow(new InternalException("Internal exception"));
    mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isInternalServerError())
        .andExpect(
            result -> assertInstanceOf(InternalException.class, result.getResolvedException()))
        .andExpect(
            result ->
                assertEquals(
                    "Internal exception",
                    Objects.requireNonNull(result.getResolvedException()).getMessage()));
  }

  @Test
  public void searchEmployeeTestWhenKeyWordNotNull() throws Exception {
    // given
    final String uri = "/employees";
    final String keyword = "o";
    final EmployeeDTO employee = new EmployeeDTO("oma", 5000, Gender.FEMALE, ContractType.CDI);
    final EmployeeDTO employee2 = new EmployeeDTO("oma1", 2000, Gender.FEMALE, ContractType.CDI);
    final List<EmployeeDTO> listOfEmployees = List.of(employee, employee2);

    // when
    when(employeeServiceImpl.searchEmployees(keyword)).thenReturn(listOfEmployees);
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
    FetchEmployeeResponse employees = super.mapFromJson(content, FetchEmployeeResponse.class);
    assertEquals(2, employees.getResult().size());
    assertEquals("oma", employees.getResult().get(0).getName());
    assertEquals("oma1", employees.getResult().get(1).getName());
  }

  @Test
  public void searchEmployeeTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/employees";
    // when
    when(employeeServiceImpl.searchEmployees(null)).thenReturn(List.of());
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
    FetchEmployeeResponse employees = super.mapFromJson(content, FetchEmployeeResponse.class);
    assertEquals(0, employees.getResult().size());
  }

  @Test
  public void fetchEmployeesWithNonNullKeywordReturnsEmployees() throws Exception {
    // given
    final String uri = "/employees";
    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setKeyword("test");
    final EmployeeDTO employee = new EmployeeDTO("oma", 5000, Gender.FEMALE, ContractType.CDI);
    final EmployeeDTO employee2 = new EmployeeDTO("oma1", 2000, Gender.FEMALE, ContractType.CDI);
    final List<EmployeeDTO> listOfEmployees = List.of(employee, employee2);
    FetchEmployeeResponse fetchEmployeeResponse = new FetchEmployeeResponse();
    fetchEmployeeResponse.setResult(listOfEmployees);

    // when
    when(employeeServiceImpl.searchEmployees(employeeRequest.getKeyword()))
        .thenReturn(fetchEmployeeResponse.getResult());

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
    assertEquals(2, result.getResult().size());
  }

  @Test
  public void fetchEmployees_WithNullKeyword_ReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/employees";
    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setKeyword("");
    final List<EmployeeDTO> listOfEmployees = List.of();

    // When
    when(employeeServiceImpl.getAllEmployees()).thenReturn(listOfEmployees);
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
    assertEquals(0, result.getResult().size());
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
    when(employeeServiceImpl.addEmployee(any(Employee.class)))
        .thenReturn("Employee added successfully");
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
    when(employeeServiceImpl.updateEmployee(any(Employee.class)))
        .thenReturn("Employee is updated successfully");
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
  public void deleteEmployeeExistTest() throws Exception {
    // Given
    String uri = "/employees/29";

    // when
    when(employeeServiceImpl.deleteEmployee(29L)).thenReturn("Employee is deleted successfully");
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("Employee is deleted successfully", content);
  }

  @Test
  public void deleteEmployeeNotExistTest() throws Exception {
    // Given
    final String uri = "/employees/70";

    // When
    when(employeeServiceImpl.deleteEmployee(70L)).thenReturn("Employee not deleted successfully");
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("Employee not deleted successfully", content);
  }
}
