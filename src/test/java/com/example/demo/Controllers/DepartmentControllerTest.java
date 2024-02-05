package com.example.demo.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.Controllers.Request.DepartmentRequest;
import com.example.demo.Controllers.Response.DepartmentResponse;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entities.Department;
import com.example.demo.ServicesImpl.DepartmentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class DepartmentControllerTest extends AbstractTest {

  @MockBean DepartmentServiceImpl departmentServiceImpl;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllDepartmentsTestWhenDepartmentExist() throws Exception {
    // Given
    final String uri = "/departments";
    final DepartmentDTO departmentDTO = new DepartmentDTO("department");
    final DepartmentDTO departmentDTO1 = new DepartmentDTO("department");
    final List<DepartmentDTO> listOfDepartments = List.of(departmentDTO, departmentDTO1);

    // When
    when(departmentServiceImpl.getAllDepartments()).thenReturn(listOfDepartments);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    DepartmentResponse departments = super.mapFromJson(content, DepartmentResponse.class);
    assertEquals(2, departments.getResult().size());
  }

  @Test
  public void getAllDepartmentsTestWhenNoDepartmentExist() throws Exception {
    // Given
    final String uri = "/departments";
    final List<DepartmentDTO> listOfDepartments = List.of();

    // When
    when(departmentServiceImpl.getAllDepartments()).thenReturn(listOfDepartments);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    DepartmentResponse departments = super.mapFromJson(content, DepartmentResponse.class);
    assertEquals(0, departments.getResult().size());
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

  public void searchDepartmentTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/departments";
    // when
    when(departmentServiceImpl.searchDepartment(null)).thenReturn(List.of());
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
    DepartmentDTO[] departments = super.mapFromJson(content, DepartmentDTO[].class);
    assertEquals(0, departments.length);
  }

  @Test
  public void fetchDepartments_WithNullKeyword_ReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/departments";
    DepartmentRequest departmentRequest = new DepartmentRequest();
    departmentRequest.setKeyword("");
    final List<DepartmentDTO> listOfDepartments = List.of();

    // When
    when(departmentServiceImpl.getAllDepartments()).thenReturn(listOfDepartments);
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
    assertEquals(0, result.getResult().size());
  }

  @Test
  public void addDepartmentTest() throws Exception {

    // Given
    final String uri = "/departments";
    Department department = new Department();
    department.setName("department");
    String inputJson = new ObjectMapper().writeValueAsString(department);
    final DepartmentDTO departmentDTO = new DepartmentDTO("department");

    // When
    when(departmentServiceImpl.addDepartment(any(Department.class))).thenReturn(departmentDTO);
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
    assertEquals(departmentDTO.getName(), result.getName());
  }

  @Test
  public void updateDepartment() throws Exception {
    // Given
    final String uri = "/departments";
    Department department = new Department();
    department.setName("department");
    String inputJson = new ObjectMapper().writeValueAsString(department);
    final DepartmentDTO departmentDTO = new DepartmentDTO("department");

    // When
    when(departmentServiceImpl.updateDepartment(any(Department.class))).thenReturn(departmentDTO);
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
    assertEquals(departmentDTO.getName(), result.getName());
  }
}
