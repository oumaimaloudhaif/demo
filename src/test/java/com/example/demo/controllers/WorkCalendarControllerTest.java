package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.request.WorkCalendarRequest;
import com.example.demo.controllers.response.WorkCalendarResponse;
import com.example.demo.dto.WorkCalendarDTO;
import com.example.demo.entities.WorkCalendar;
import com.example.demo.servicesImpl.WorkCalendarServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class WorkCalendarControllerTest extends AbstractTest {

  @MockBean WorkCalendarServiceImpl workCalendarServiceImpl;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllWorkCalendarsTestWhenWorkCalendarExist() throws Exception {
    // Given
    final String uri = "/workCalendars";
    final WorkCalendarDTO workCalendarDTO = new WorkCalendarDTO("tag");
    final WorkCalendarDTO workCalendarDTO1 = new WorkCalendarDTO("tag1");
    final List<WorkCalendarDTO> workCalendarDTOS = List.of(workCalendarDTO, workCalendarDTO1);

    // When
    when(workCalendarServiceImpl.getAllWorkCalendars()).thenReturn(workCalendarDTOS);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    WorkCalendarResponse workCalendars = super.mapFromJson(content, WorkCalendarResponse.class);
    assertEquals(2, workCalendars.getResult().size());
    assertEquals("tag", workCalendars.getResult().get(0).getTag());
    assertEquals("tag1", workCalendars.getResult().get(1).getTag());
  }

  @Test
  public void getAllWorkCalendarsTestWhenNoWorkCalendarExist() throws Exception {
    // Given
    final String uri = "/workCalendars";
    final List<WorkCalendarDTO> listOfWorkCalendars = List.of();

    // When
    when(workCalendarServiceImpl.getAllWorkCalendars()).thenReturn(listOfWorkCalendars);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    WorkCalendarResponse workCalendars = super.mapFromJson(content, WorkCalendarResponse.class);
    assertEquals(0, workCalendars.getResult().size());
  }

  @Test
  public void getAllWorkCalendarsTestWrongPath() throws Exception {
    // given
    final String uri = "/workCalendarss";

    // when
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }

  @Test
  public void searchWorkCalendarTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/workCalendars";
    // when
    when(workCalendarServiceImpl.searchWorkCalendars(null)).thenReturn(List.of());
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
    WorkCalendarResponse workCalendars = super.mapFromJson(content, WorkCalendarResponse.class);
    assertEquals(0, workCalendars.getResult().size());
  }

  @Test
  public void fetchWorkCalendarsWithNullKeywordReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/workCalendars";
    WorkCalendarRequest workCalendarRequest = new WorkCalendarRequest();
    workCalendarRequest.setKeyword("");
    final List<WorkCalendarDTO> listOfWorkCalendars = List.of();

    // When
    when(workCalendarServiceImpl.getAllWorkCalendars()).thenReturn(listOfWorkCalendars);
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(workCalendarRequest.getKeyword())))
            .andExpect(status().isOk())
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    WorkCalendarResponse result = objectMapper.readValue(content, WorkCalendarResponse.class);
    assertEquals(0, result.getResult().size());
  }

  @Test
  public void addWorkCalendarTest() throws Exception {

    // Given
    final String uri = "/workCalendars";
    WorkCalendar WorkCalendar = new WorkCalendar();
    WorkCalendar.setTag("tag");
    String inputJson = new ObjectMapper().writeValueAsString(WorkCalendar);
    final WorkCalendarDTO workCalendarDTO = new WorkCalendarDTO("tag");

    // When
    when(workCalendarServiceImpl.addWorkCalendar(any(WorkCalendar.class)))
        .thenReturn(workCalendarDTO);
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
    WorkCalendarDTO result = objectMapper.readValue(content, WorkCalendarDTO.class);
    assertEquals(workCalendarDTO.getStartTime(), result.getStartTime());
    assertEquals(workCalendarDTO.getTag(), result.getTag());
  }

  @Test
  public void updateWorkCalendar() throws Exception {
    // Given
    final String uri = "/workCalendars";

    WorkCalendar WorkCalendar = new WorkCalendar();
    WorkCalendar.setTag("tag");
    String inputJson = new ObjectMapper().writeValueAsString(WorkCalendar);
    final WorkCalendarDTO workCalendarDTO = new WorkCalendarDTO("tag");

    // When
    when(workCalendarServiceImpl.updateWorkCalendar(any(WorkCalendar.class)))
        .thenReturn(workCalendarDTO);
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
    WorkCalendarDTO result = objectMapper.readValue(content, WorkCalendarDTO.class);
    assertEquals(workCalendarDTO.getStartTime(), result.getStartTime());
  }

  @Test
  public void getWorkCalendarNotExistTest() throws Exception {
    // Given
    String uri = "/workCalendars/39";

    // When
    when(workCalendarServiceImpl.getWorkCalendarById(39L)).thenReturn(null);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("", content);
  }

  @Test
  public void getWorkCalendarsExistTest() throws Exception {
    // Given
    String uri = "/workCalendars/11";
    final WorkCalendarDTO workCalendarDTO = new WorkCalendarDTO("tag");

    // When
    when(workCalendarServiceImpl.getWorkCalendarById(11L)).thenReturn(workCalendarDTO);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    WorkCalendarDTO result = objectMapper.readValue(content, WorkCalendarDTO.class);
    assertEquals(workCalendarDTO.getTag(), result.getTag());
  }

  @Test
  public void deleteWorkCalendarsNotExistTest() throws Exception {
    // Given
    String uri = "/workCalendars/39";

    // When
    when(workCalendarServiceImpl.deleteWorkCalendarById(39L)).thenReturn(false);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    Boolean actualValue = Boolean.valueOf(content);
    assertEquals(false, actualValue);
  }

  @Test
  public void deleteWorkCalendarsExistTest() throws Exception {
    // Given
    String uri = "/workCalendars/1";

    // When
    when(workCalendarServiceImpl.deleteWorkCalendarById(1L)).thenReturn(true);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    Boolean actualValue = Boolean.valueOf(content);
    assertEquals(true, actualValue);
  }
}
