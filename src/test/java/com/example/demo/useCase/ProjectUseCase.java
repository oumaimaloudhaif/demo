package com.example.demo.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.AbstractTest;
import com.example.demo.controllers.request.ProjectRequest;
import com.example.demo.controllers.response.ProjectResponse;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.entities.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class ProjectUseCase extends AbstractTest {
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void fetchAllProjectsTest() throws Exception {
    // Given
    String url = "/projects";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ProjectResponse projects = objectMapper.readValue(content, ProjectResponse.class);
    Assertions.assertEquals(4, projects.getResult().size());
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

  @Test
  public void searchProjectTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/projects";
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
    ProjectResponse projects = objectMapper.readValue(content, ProjectResponse.class);
    assertEquals(3, projects.getResult().size());
  }

  @Test
  public void getProjectsWithNullKeywordReturnsListOfProjects() throws Exception {
    // Given
    final String uri = "/projects";
    ProjectRequest projectRequest = new ProjectRequest();
    projectRequest.setKeyword("");

    // When
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
    assertEquals(4, result.getResult().size());
  }

  @Test
  public void addProjectTest() throws Exception {

    // Given
    final String uri = "/projects";
    Project project = new Project();
    project.setName("Project A");
    String inputJson = new ObjectMapper().writeValueAsString(project);

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
    ProjectDTO result = objectMapper.readValue(content, ProjectDTO.class);
    assertEquals("Project A", result.getName());
  }

  @Test
  public void updateProject() throws Exception {
    // Given
    final String uri = "/projects";
    Project project = new Project();
    project.setName("Project A1");
    String inputJson = new ObjectMapper().writeValueAsString(project);

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
    ProjectDTO result = objectMapper.readValue(content, ProjectDTO.class);
    assertEquals("Project A1", result.getName());
  }

  @Test
  public void findProjectById() throws Exception {
    // Given
    final String uri = "/projects/3";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    ProjectDTO result = objectMapper.readValue(content, ProjectDTO.class);
    assertEquals("Project C", result.getName());
  }

  @Test
  public void deleteProjectNotExistTest() throws Exception {
    // Given
    String uri = "/projects/39";

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
  public void deleteProjectExistTest() throws Exception {
    // Given
    String uri = "/projects/2";

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
