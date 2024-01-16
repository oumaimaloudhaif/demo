package com.example.demo.Controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.Controllers.Request.EmployeeRequest;
import com.example.demo.Controllers.Response.FetchEmployeeResponse;
import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Entities.Employee;
import com.example.demo.ServicesImpl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
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
    // given
    String uri = "/employees";
    EmployeeDTO employee = new EmployeeDTO();
    employee.setName("ouma");
    employee.setSalary(5000);
    EmployeeDTO employee2 = new EmployeeDTO();
    employee2.setName("ouma1");
    employee2.setSalary(5000);
    List<EmployeeDTO> listOfEmployees = List.of(employee, employee2);
    // when
    when(employeeServiceImpl.getAllEmployees()).thenReturn(listOfEmployees);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    FetchEmployeeResponse employees = super.mapFromJson(content, FetchEmployeeResponse.class);
    assertEquals(2, employees.getResult().size());
  }

  @Test
  public void getAllEmployeesTestWhenNoEmployeeExist() throws Exception {
    // given
    String uri = "/employees";
    List<EmployeeDTO> listOfEmployees = List.of();

    // when
    when(employeeServiceImpl.getAllEmployees()).thenReturn(listOfEmployees);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    FetchEmployeeResponse employees = super.mapFromJson(content, FetchEmployeeResponse.class);
    assertEquals(0, employees.getResult().size());
  }

  @Test
  public void getAllEmployeesTestWrongPath() throws Exception {
    // given
    String uri = "/emloyees";

    // when
    mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  public void getAllEmployeesTestThenThrowException() throws Exception {
    // given
    String uri = "/employees";

    // when
    when(employeeServiceImpl.getAllEmployees()).thenThrow(new RuntimeException());
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    MockHttpServletResponse response = mvcResult.getResponse();
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }

  @Test
  public void searchEmployeeTestWhenKeyWordNotNull() throws Exception {
    // given
    String uri = "/employees";
    String keyword = "o";
    EmployeeDTO employee = new EmployeeDTO();
    employee.setName("ouma");
    employee.setSalary(5000);
    EmployeeDTO employee2 = new EmployeeDTO();
    employee2.setName("ouma1");
    employee2.setSalary(6000);
    List<EmployeeDTO> listOfEmployees = List.of(employee, employee2);

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
    assertEquals("ouma", employees.getResult().get(0).getName());
    assertEquals("ouma1", employees.getResult().get(1).getName());
  }

  public void searchEmployeeTestWhenKeywordIsNull() throws Exception {
    // given
    String uri = "/searchEmployee";
    String keyword = null;
    // when
    when(employeeServiceImpl.searchEmployees(keyword)).thenReturn(List.of());
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
    EmployeeDTO[] employees = super.mapFromJson(content, EmployeeDTO[].class);
    assertEquals(0, employees.length);
  }

  @Test
  public void fetchEmployees_WithNonNullKeyword_ReturnsEmployees() throws Exception {
    // given
    String uri = "/employees";
    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setKeyword("test");
    EmployeeDTO employee = new EmployeeDTO();
    employee.setName("ouma");
    employee.setSalary(5000);
    EmployeeDTO employee2 = new EmployeeDTO();
    employee2.setName("ouma1");
    employee2.setSalary(6000);
    List<EmployeeDTO> listOfEmployees = List.of(employee, employee2);
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
    // given
    String uri = "/employees";
    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setKeyword("");
    List<EmployeeDTO> listOfEmployees = List.of();
    // when
    when(employeeServiceImpl.searchEmployees(employeeRequest.getKeyword()))
        .thenReturn(listOfEmployees);

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
    assertEquals(0, result.getResult().size());
  }

  @Test
  public void addEmployeeTest() throws Exception {

    // given
    String uri = "/employees";
    Employee employee = new Employee();
    employee.setName("oumaima");
    employee.setSalary(50000);
    String inputJson = new ObjectMapper().writeValueAsString(employee);

    // when
    when(employeeServiceImpl.addEmployee(any(Employee.class)))
        .thenReturn("Employee added successfully");
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    // then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("Employee added successfully", content);
  }

  @Test
  public void updateEmployee() throws Exception {
    // given
    String uri = "/employees";
    Employee employee = new Employee();
    employee.setEmployee_id(1L);
    employee.setName("ouma1");
    employee.setSalary(5000);
    String inputJson = new ObjectMapper().writeValueAsString(employee);

    // when
    when(employeeServiceImpl.updateEmployee(any(Employee.class)))
        .thenReturn("Employee is updated successfully");
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.put(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("Employee is updated successfully", content);
  }

  @Test
  public void deleteEmployeeExistTest() throws Exception {
    // given
    String uri = "/employees/29";

    // when
    when(employeeServiceImpl.deleteEmployee(29L)).thenReturn("Employee is deleted successfully");
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    /// then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("Employee is deleted successfully", content);
  }

  @Test
  public void deleteEmployeeNotExistTest() throws Exception {
    // given
    String uri = "/employees/70";

    // when
    when(employeeServiceImpl.deleteEmployee(70L)).thenReturn("Employee not deleted successfully");
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    /// then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("Employee not deleted successfully", content);
  }
}
