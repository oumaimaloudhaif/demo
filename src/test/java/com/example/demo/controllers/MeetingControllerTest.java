package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controllers.request.MeetingRequest;
import com.example.demo.controllers.response.MeetingResponse;
import com.example.demo.dto.MeetingDTO;
import com.example.demo.entities.Meeting;
import com.example.demo.servicesImpl.MeetingServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class MeetingControllerTest extends AbstractTest {

  @MockBean MeetingServiceImpl meetingServiceImpl;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllMeetingsTestWhenMeetingExist() throws Exception {
    // Given
    final String uri = "/meetings";
    final MeetingDTO meetingDTO = new MeetingDTO("meeting");
    final MeetingDTO meetingDTO1 = new MeetingDTO("meeting");
    final List<MeetingDTO> listOfMeetings = List.of(meetingDTO, meetingDTO1);

    // When
    when(meetingServiceImpl.getAllMeetings()).thenReturn(listOfMeetings);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    MeetingResponse meetings = super.mapFromJson(content, MeetingResponse.class);
    assertEquals(2, meetings.getResult().size());
  }

  @Test
  public void getAllMeetingsTestWhenNoMeetingExist() throws Exception {
    // Given
    final String uri = "/meetings";
    final List<MeetingDTO> listOfMeetings = List.of();

    // When
    when(meetingServiceImpl.getAllMeetings()).thenReturn(listOfMeetings);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    MeetingResponse meetings = super.mapFromJson(content, MeetingResponse.class);
    assertEquals(0, meetings.getResult().size());
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
    when(meetingServiceImpl.searchMeeting(null)).thenReturn(List.of());
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
    MeetingResponse meetings = super.mapFromJson(content, MeetingResponse.class);
    assertEquals(0, meetings.getResult().size());
  }

  @Test
  public void getMeetingsWithNullKeywordReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/meetings";
    MeetingRequest meetingRequest = new MeetingRequest();
    meetingRequest.setKeyword("");
    final List<MeetingDTO> listOfMeetings = List.of();

    // When
    when(meetingServiceImpl.getAllMeetings()).thenReturn(listOfMeetings);
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
    assertEquals(0, result.getResult().size());
  }

  @Test
  public void addMeetingTest() throws Exception {

    // Given
    final String uri = "/meetings";
    Meeting meeting = new Meeting();
    meeting.setTitle("meeting");
    String inputJson = new ObjectMapper().writeValueAsString(meeting);
    final MeetingDTO meetingDTO = new MeetingDTO("meeting");

    // When
    when(meetingServiceImpl.addMeeting(any(Meeting.class))).thenReturn(meetingDTO);
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
    assertEquals(meetingDTO.getTitle(), result.getTitle());
  }

  @Test
  public void updateMeeting() throws Exception {
    // Given
    final String uri = "/meetings";
    Meeting meeting = new Meeting();
    meeting.setTitle("meeting");
    String inputJson = new ObjectMapper().writeValueAsString(meeting);
    final MeetingDTO meetingDTO = new MeetingDTO("meeting");

    // When
    when(meetingServiceImpl.updateMeeting(any(Meeting.class))).thenReturn(meetingDTO);
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
    assertEquals(meetingDTO.getTitle(), result.getTitle());
  }

  @Test
  public void getMeetingNotExistTest() throws Exception {
    // Given
    String uri = "/meetings/30";

    // When
    when(meetingServiceImpl.getMeetingById(30L)).thenReturn(null);
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
  public void getMeetingExistTest() throws Exception {
    // Given
    String uri = "/meetings/1";
    final MeetingDTO meetingDTO = new MeetingDTO("meeting");

    // When
    when(meetingServiceImpl.getMeetingById(1L)).thenReturn(meetingDTO);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    MeetingDTO result = objectMapper.readValue(content, MeetingDTO.class);
    assertEquals(meetingDTO.getTitle(), result.getTitle());
  }

  @Test
  public void deleteMeetingNotExistTest() throws Exception {
    // Given
    String uri = "/meetings/30";

    // When
    when(meetingServiceImpl.deleteMeetingById(30L)).thenReturn(false);
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
  public void deleteMeetingExistTest() throws Exception {
    // Given
    String uri = "/meetings/1";

    // When
    when(meetingServiceImpl.deleteMeetingById(1L)).thenReturn(true);
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
