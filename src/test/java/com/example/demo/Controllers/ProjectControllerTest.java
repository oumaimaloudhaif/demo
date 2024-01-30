package com.example.demo.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.Controllers.Request.ProjectRequest;
import com.example.demo.Controllers.Response.ProjectResponse;
import com.example.demo.Dto.ProjectDTO;
import com.example.demo.Entities.Employee;
import com.example.demo.Entities.Project;
import com.example.demo.Enums.ContractType;
import com.example.demo.Enums.Gender;
import com.example.demo.ServicesImpl.ProjectServiceImpl;
import com.example.demo.tools.EmployeeTools;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class ProjectControllerTest extends AbstractTest {

  @MockBean ProjectServiceImpl projectServiceImpl;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllProjectsTestWhenProjectExist() throws Exception {
    // Given
    final String uri = "/projects";
    List<Employee> employees =
            Arrays.asList(
                    new Employee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI),
                    new Employee(2L, "Oumaima", 1200, Gender.FEMALE, ContractType.CDI));
    final ProjectDTO project = new ProjectDTO("project",employees);
    final ProjectDTO project2 = new ProjectDTO("project1",employees);
    final List<ProjectDTO> listOfProjects = List.of(project, project2);

    // When
    when(projectServiceImpl.getAllProjects()).thenReturn(listOfProjects);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ProjectResponse projects = super.mapFromJson(content, ProjectResponse.class);
    assertEquals(2, projects.getResult().size());
  }

  @Test
  public void getAllProjectsTestWhenNoProjectExist() throws Exception {
    // Given
    final String uri = "/projects";
    final List<ProjectDTO> listOfProjects = List.of();

    // When
    when(projectServiceImpl.getAllProjects()).thenReturn(listOfProjects);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ProjectResponse projects = super.mapFromJson(content, ProjectResponse.class);
    assertEquals(0, projects.getResult().size());
  }

  @Test
  public void getAllProjectsTestWrongPath() throws Exception {
    // given
    final String uri = "/projectss";

    // when
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }

  public void searchProjectTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/projects";
    // when
    when(projectServiceImpl.searchProject(null)).thenReturn(List.of());
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
    ProjectDTO[] projects = super.mapFromJson(content, ProjectDTO[].class);
    assertEquals(0, projects.length);
  }

  @Test
  public void getProjects_WithNullKeyword_ReturnsListOfProjects() throws Exception {
    // Given
    final String uri = "/projects";
    ProjectRequest projectRequest = new ProjectRequest();
    projectRequest.setKeyword("");
    Employee employee1 =
            EmployeeTools.createEmployee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    Employee employee2 =
            EmployeeTools.createEmployee(2L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final List<Employee> employees = Arrays.asList(employee1, employee2);
    final ProjectDTO project = new ProjectDTO("project-Test",employees);
    final ProjectDTO project2 = new ProjectDTO("project-Test",employees);
    final List<ProjectDTO> listOfProjects = List.of(project, project2);

    // When
    when(projectServiceImpl.getAllProjects()).thenReturn(listOfProjects);
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(projectRequest.getKeyword())))
            .andExpect(status().isOk())
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ProjectResponse result = objectMapper.readValue(content, ProjectResponse.class);
    assertEquals(2, result.getResult().size());
  }

  @Test
  public void addProjectTest() throws Exception {

    // Given
    final String uri = "/projects";
    Project project = new Project();
    project.setName("project");
    String inputJson = new ObjectMapper().writeValueAsString(project);
    Employee employee1 =
            EmployeeTools.createEmployee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    Employee employee2 =
            EmployeeTools.createEmployee(2L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final List<Employee> employees = Arrays.asList(employee1, employee2);
    final ProjectDTO projectDTO = new ProjectDTO("project",employees);

    // When
    when(projectServiceImpl.addProject(any(Project.class))).thenReturn(projectDTO);
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
    ProjectDTO result = objectMapper.readValue(content, ProjectDTO.class);
    assertEquals(projectDTO.name(), result.name());
  }

  @Test
  public void updateProject() throws Exception {
    // Given
    final String uri = "/projects";
    Project project = new Project();
    project.setName("project");
    String inputJson = new ObjectMapper().writeValueAsString(project);
    Employee employee1 =
            EmployeeTools.createEmployee(1L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    Employee employee2 =
            EmployeeTools.createEmployee(2L, "Oumaima L", 1000, Gender.FEMALE, ContractType.CDI);
    final List<Employee> employees = Arrays.asList(employee1, employee2);
    final ProjectDTO projectDTO = new ProjectDTO("project",employees);

    // When
    when(projectServiceImpl.updateProject(any(Project.class))).thenReturn(projectDTO);
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
    ProjectDTO result = objectMapper.readValue(content, ProjectDTO.class);
    assertEquals(projectDTO.name(), result.name());
  }
}
