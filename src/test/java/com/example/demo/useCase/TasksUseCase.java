package com.example.demo.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.AbstractTest;
import com.example.demo.controllers.request.TaskRequest;
import com.example.demo.controllers.response.TaskResponse;
import com.example.demo.dto.TaskDTO;
import com.example.demo.entities.Task;
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
class TasksUseCase extends AbstractTest {
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  @Order(1)
  public void fetchAllTasksTest() throws Exception {
    // Given
    String url = "/tasks";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    TaskResponse reports = super.mapFromJson(content, TaskResponse.class);
    assertEquals(3, reports.getResult().size());
  }

  @Test
  @Order(2)
  public void addTaskTest() throws Exception {

    // Given
    final String uri = "/tasks";
    Task task = new Task();
    task.setName("Task 1");
    String inputJson = new ObjectMapper().writeValueAsString(task);
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
    TaskDTO result = objectMapper.readValue(content, TaskDTO.class);
    assertEquals("Task 1", result.getName());
  }

  @Test
  @Order(2)
  public void getAllTasksTestWrongPath() throws Exception {
    // given
    final String uri = "/taskss";

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
  public void searchTaskTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/tasks";
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
    TaskResponse tasks = super.mapFromJson(content, TaskResponse.class);
    assertEquals(4, tasks.getResult().size());
  }

  @Test
  @Order(4)
  public void updateTask() throws Exception {
    // Given
    final String uri = "/tasks";
    Task task = new Task();
    task.setId_Task(1L);
    task.setName("Task 11");
    String inputJson = new ObjectMapper().writeValueAsString(task);

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
    TaskDTO result = objectMapper.readValue(content, TaskDTO.class);
    assertEquals("Task 11", result.getName());
  }

  @Test
  @Order(5)
  public void findTaskById() throws Exception {
    // Given
    final String uri = "/tasks/1";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    TaskDTO result = objectMapper.readValue(content, TaskDTO.class);
    assertEquals("Task 11", result.getName());
  }

  @Test
  @Order(6)
  public void deleteTaskNotExistTest() throws Exception {
    // Given
    String uri = "/tasks/18";

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
  @Order(7)
  public void deleteTaskExistTest() throws Exception {
    // Given
    String uri = "/tasks/1";

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
  @Order(8)
  public void fetchTasksWithNullKeywordInTaskRequest() throws Exception {
    // Given
    final String uri = "/tasks";
    TaskRequest taskRequest = new TaskRequest();
    taskRequest.setKeyword("");
    // When
    MvcResult mvcResult =
            mvc.perform(
                            MockMvcRequestBuilders.get(uri)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(taskRequest.getKeyword())))
                    .andExpect(status().isOk())
                    .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    TaskResponse result = objectMapper.readValue(content, TaskResponse.class);
    assertEquals(3, result.getResult().size());
  }
}
