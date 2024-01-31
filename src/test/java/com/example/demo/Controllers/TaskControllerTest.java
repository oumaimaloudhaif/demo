package com.example.demo.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.Controllers.Request.TaskRequest;
import com.example.demo.Controllers.Response.TaskResponse;
import com.example.demo.Dto.TaskDTO;
import com.example.demo.Entities.Task;
import com.example.demo.Enums.Priority;
import com.example.demo.Enums.TaskStatus;
import com.example.demo.ServicesImpl.TaskServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class TaskControllerTest extends AbstractTest {

  @MockBean TaskServiceImpl taskServiceImpl;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllTasksTestWhenTaskExist() throws Exception {
    // Given
    final String uri = "/tasks";
    final TaskDTO taskDTO =
        new TaskDTO("task", "description", Priority.HIGH, TaskStatus.IN_PROGRESS);
    final TaskDTO taskDTO1 =
        new TaskDTO("task", "description", Priority.HIGH, TaskStatus.IN_PROGRESS);
    final List<TaskDTO> listOfTasks = List.of(taskDTO, taskDTO1);

    // When
    when(taskServiceImpl.getAllTasks()).thenReturn(listOfTasks);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    TaskResponse tasks = super.mapFromJson(content, TaskResponse.class);
    assertEquals(2, tasks.getResult().size());
  }

  @Test
  public void getAllTasksTestWhenNoTaskExist() throws Exception {
    // Given
    final String uri = "/tasks";
    final List<TaskDTO> listOfTasks = List.of();

    // When
    when(taskServiceImpl.getAllTasks()).thenReturn(listOfTasks);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    TaskResponse tasks = super.mapFromJson(content, TaskResponse.class);
    assertEquals(0, tasks.getResult().size());
  }

  @Test
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

  public void searchTaskTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/tasks";
    // when
    when(taskServiceImpl.searchTasks(null)).thenReturn(List.of());
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
    TaskDTO[] tasks = super.mapFromJson(content, TaskDTO[].class);
    assertEquals(0, tasks.length);
  }

  @Test
  public void fetchTasks_WithNullKeyword_ReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/tasks";
    TaskRequest taskRequest = new TaskRequest();
    taskRequest.setKeyword("");
    final List<TaskDTO> listOfTasks = List.of();

    // When
    when(taskServiceImpl.getAllTasks()).thenReturn(listOfTasks);
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
    assertEquals(0, result.getResult().size());
  }

  @Test
  public void addTaskTest() throws Exception {

    // Given
    final String uri = "/tasks";
    Task task = new Task();
    task.setName("task");
    String inputJson = new ObjectMapper().writeValueAsString(task);
    final TaskDTO taskDTO =
        new TaskDTO("task", "description", Priority.HIGH, TaskStatus.IN_PROGRESS);

    // When
    when(taskServiceImpl.addTask(any(Task.class))).thenReturn(taskDTO);
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
    assertEquals(taskDTO.getName(), result.getName());
  }

  @Test
  public void updateTask() throws Exception {
    // Given
    final String uri = "/tasks";
    Task task = new Task();
    task.setName("task");
    String inputJson = new ObjectMapper().writeValueAsString(task);
    final TaskDTO taskDTO =
        new TaskDTO("task", "description", Priority.HIGH, TaskStatus.IN_PROGRESS);

    // When
    when(taskServiceImpl.updateTask(any(Task.class))).thenReturn(taskDTO);
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
    assertEquals(taskDTO.getName(), result.getName());
  }
}
