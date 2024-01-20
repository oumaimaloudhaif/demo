package com.example.demo.ServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.MeetingDTO;
import com.example.demo.Entities.Meeting;
import com.example.demo.Repository.MeetingRepository;
import java.util.Arrays;

import java.util.List;

import com.example.demo.tools.MeetingDTOTools;
import com.example.demo.tools.MeetingTools;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = DemoApplication.class)
@AutoConfigureMockMvc
public class MeetingServiceImplTest {
  @MockBean
  private MeetingRepository meetingRepository;
  @Autowired
  private MeetingServiceImpl meetingService;
  @MockBean private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllMeetings() {
    // Given
    final Meeting meeting= MeetingTools.createMeeting(1L, "Meeting");
    final Meeting meeting1= MeetingTools.createMeeting(2L, "Meeting1");
    final MeetingDTO meetingDTO= MeetingDTOTools.createMeeting( "Meeting");
    final MeetingDTO meeting1DTO= MeetingDTOTools.createMeeting("Meeting1");
    final List<Meeting> mockedMeetings = Arrays.asList(meeting,meeting1);

    // When
    when(meetingRepository.findAll()).thenReturn(mockedMeetings);
    when(fromDOToDTO.MapMeeting(meeting)).thenReturn(meetingDTO);
    when(fromDOToDTO.MapMeeting(meeting1)).thenReturn(meeting1DTO);
    final List<MeetingDTO> Meetings = meetingService.getAllMeetings();

    // Then
    assertEquals(mockedMeetings.size(), Meetings.size());
  }

  @Test
  public void testSearchMeetings() {
    // Given
    final  String keyword = "Oumaima";
    final Meeting meeting= MeetingTools.createMeeting(1L, "Meeting");
    final Meeting meeting1= MeetingTools.createMeeting(2L, "Meeting1");
    final MeetingDTO meetingDTO= MeetingDTOTools.createMeeting( "Meeting");
    final MeetingDTO meeting1DTO= MeetingDTOTools.createMeeting("Meeting1");
    final List<Meeting> mockedMeetings = Arrays.asList(meeting,meeting1);

    // When
    when(meetingRepository.findByTitle(keyword)).thenReturn(mockedMeetings);
    when(fromDOToDTO.MapMeeting(meeting)).thenReturn(meetingDTO);
    when(fromDOToDTO.MapMeeting(meeting1)).thenReturn(meeting1DTO);
    final List<MeetingDTO> Meetings = meetingService.searchMeeting(keyword);

    // Then
    assertEquals(mockedMeetings.size(), Meetings.size());
  }

  @Test
  public void testAddMeeting() {
    // Given
    final Meeting inputMeeting= MeetingTools.createMeeting(1L, "Meeting");
    final  MeetingDTO expectedMeetingDTO = MeetingDTOTools.createMeeting("Meeting1");

    // When
    when(meetingRepository.save(inputMeeting)).thenReturn(inputMeeting);
    when(fromDOToDTO.MapMeeting(inputMeeting)).thenReturn(expectedMeetingDTO);
    final MeetingDTO resultMeetingDTO = meetingService.addMeeting(inputMeeting);

    // Then
    assertEquals(expectedMeetingDTO, resultMeetingDTO);
  }

  @Test
  public void testUpdateMeeting() {
    // Given
    final Meeting updatedMeeting= MeetingTools.createMeeting(1L, "Meeting");
    final MeetingDTO expectedMeetingDTO = MeetingDTOTools.createMeeting("Meeting1");

    // When
    when(meetingRepository.save(updatedMeeting)).thenReturn(updatedMeeting);
    when(fromDOToDTO.MapMeeting(updatedMeeting)).thenReturn(expectedMeetingDTO);
    final MeetingDTO resultMeetingDTO = meetingService.addMeeting(updatedMeeting);

    // Then
    assertEquals(expectedMeetingDTO, resultMeetingDTO);
  }
}
