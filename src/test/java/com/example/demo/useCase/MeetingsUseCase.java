package com.example.demo.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.AbstractTest;
import com.example.demo.controllers.request.MeetingRequest;
import com.example.demo.controllers.response.MeetingResponse;
import com.example.demo.dto.MeetingDTO;
import com.example.demo.entities.Meeting;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class MeetingsUseCase extends AbstractTest {
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void fetchAllMeetingsTest() throws Exception {
    // Given
    String url = "/meetings";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    MeetingResponse result = objectMapper.readValue(content, MeetingResponse.class);
    assertEquals(2, result.getResult().size());
  }

  @Test
  public void getAllMeetingsTestWrongPath() throws Exception {
    // given
    final String uri = "/meetingss";

    // when
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }

  @Test
  public void searchMeetingTestWhenKeywordIsNull() throws Exception {
    // given
    final String uri = "/meetings";

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
    MeetingResponse result = objectMapper.readValue(content, MeetingResponse.class);
    assertEquals(4, result.getResult().size());
  }

  @Test
  public void getMeetingsWithNullKeywordReturnsNotEmptyList() throws Exception {
    // Given
    final String uri = "/meetings";
    MeetingRequest meetingRequest = new MeetingRequest();
    meetingRequest.setKeyword("");

    // When
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(meetingRequest.getKeyword())))
            .andExpect(status().isOk())
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    MeetingResponse result = objectMapper.readValue(content, MeetingResponse.class);
    assertEquals(2, result.getResult().size());
  }

  @Test
  public void addMeetingTest() throws Exception {

    // Given
    final String uri = "/meetings";
    Meeting meeting = new Meeting();
    meeting.setTitle("Team Meeting");
    String inputJson = new ObjectMapper().writeValueAsString(meeting);

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
    MeetingDTO result = objectMapper.readValue(content, MeetingDTO.class);
    assertEquals("Team Meeting", result.getTitle());
  }

  @Test
  public void updateMeeting() throws Exception {
    // Given
    final String uri = "/meetings";
    Meeting meeting = new Meeting();
    meeting.setTitle("Team Meeting1");
    String inputJson = new ObjectMapper().writeValueAsString(meeting);

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
    MeetingDTO result = objectMapper.readValue(content, MeetingDTO.class);
    assertEquals("Team Meeting1", result.getTitle());
  }

  @Test
  public void findMeetingById() throws Exception {
    // Given
    final String uri = "/meetings/1";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    MeetingDTO result = objectMapper.readValue(content, MeetingDTO.class);
    assertEquals("Team Meeting", result.getTitle());
  }

  @Test
  public void deleteMeetingNotExistTest() throws Exception {
    // Given
    String uri = "/meetings/39";

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
  public void deleteMeetingExistTest() throws Exception {
    // Given
    String uri = "/meetings/1";

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
