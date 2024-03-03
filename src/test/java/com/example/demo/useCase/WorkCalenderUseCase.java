package com.example.demo.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.AbstractTest;
import com.example.demo.controllers.request.WorkCalendarRequest;
import com.example.demo.controllers.response.WorkCalendarResponse;
import com.example.demo.dto.WorkCalendarDTO;
import com.example.demo.entities.WorkCalendar;
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
class WorkCalenderUseCase extends AbstractTest {
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  @Order(1)
  public void getAllWorkCalendarsTestWhenWorkCalendarExist() throws Exception {
    // Given
    final String uri = "/workCalendars";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    WorkCalendarResponse workCalendars = super.mapFromJson(content, WorkCalendarResponse.class);
    assertEquals(2, workCalendars.getResult().size());
  }

  @Test
  @Order(2)
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
  @Order(3)
  public void addWorkCalendarTest() throws Exception {

    // Given
    final String uri = "/workCalendars";
    WorkCalendar WorkCalendar = new WorkCalendar();
    WorkCalendar.setTag("Meeting");
    String inputJson = new ObjectMapper().writeValueAsString(WorkCalendar);

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
    WorkCalendarDTO result = objectMapper.readValue(content, WorkCalendarDTO.class);
    assertEquals("Meeting", result.getTag());
  }

  @Test
  @Order(4)
  public void searchWorkCalendarTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/workCalendars";
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
    WorkCalendarResponse workCalendars = super.mapFromJson(content, WorkCalendarResponse.class);
    assertEquals(3, workCalendars.getResult().size());
  }


  @Test
  @Order(5)
  public void updateWorkCalendar() throws Exception {
    // Given
    final String uri = "/workCalendars";

    WorkCalendar WorkCalendar = new WorkCalendar();
    WorkCalendar.setId_WorkCalendar(1L);
    WorkCalendar.setTag("Meeting1");
    String inputJson = new ObjectMapper().writeValueAsString(WorkCalendar);
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
    WorkCalendarDTO result = objectMapper.readValue(content, WorkCalendarDTO.class);
    assertEquals("Meeting1", result.getTag());
  }

  @Test
  @Order(6)
  public void findWorkCalendarById() throws Exception {
    // Given
    final String uri = "/workCalendars/2";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    WorkCalendarDTO result = objectMapper.readValue(content, WorkCalendarDTO.class);
    assertEquals("Training", result.getTag());
  }

  @Test
  @Order(7)
  public void deleteWorkCalendarNotExistTest() throws Exception {
    // Given
    String uri = "/workCalendars/118";

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
  @Order(8)
  public void deleteWorkCalendarExistTest() throws Exception {
    // Given
    String uri = "/workCalendars/1";

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
  @Order(9)
  public void fetchWorkCalendarsWithNullKeywordReturnsWorkCalendarList() throws Exception {
    // Given
    final String uri = "/workCalendars";
    WorkCalendarRequest workCalendarRequest = new WorkCalendarRequest();
    workCalendarRequest.setKeyword("");
    // When
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
    assertEquals(2, result.getResult().size());
  }
}
