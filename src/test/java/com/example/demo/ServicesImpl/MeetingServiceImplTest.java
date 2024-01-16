package com.example.demo.ServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.DemoApplication;
import com.example.demo.Dto.Mappers.FromDOToDTO;
import com.example.demo.Dto.MeetingDTO;
import com.example.demo.Entities.Meeting;
import com.example.demo.Repository.MeetingRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = DemoApplication.class)
@AutoConfigureMockMvc
public class MeetingServiceImplTest {
  @Mock private MeetingRepository meetingRepository;
  @InjectMocks private MeetingServiceImpl meetingService;
  @Mock private FromDOToDTO fromDOToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  Date date = new Date(2024, Calendar.JANUARY, 13);

  @Test
  public void testGetAllMeetings() {
    List<Meeting> mockedMeetings =
        Arrays.asList(
            new Meeting(1L, "Meeting1", LocalDateTime.of(2024, 1, 13, 12, 0), date, date),
            new Meeting(2L, "Meeting1", LocalDateTime.of(2024, 1, 13, 12, 0), date, date));
    when(meetingRepository.findAll()).thenReturn(mockedMeetings);
    List<MeetingDTO> Meetings = meetingService.getAllMeetings();
    assertEquals(mockedMeetings.size(), Meetings.size());
  }

  @Test
  public void testSearchMeetings() {
    String keyword = "Oumaima";
    List<Meeting> mockedMeetings =
        Arrays.asList(
            new Meeting(1L, "Meeting1", LocalDateTime.of(2024, 1, 13, 12, 0), date, date),
            new Meeting(2L, "Meeting1", LocalDateTime.of(2024, 1, 13, 12, 0), date, date));
    when(meetingRepository.findByTitle(keyword)).thenReturn(mockedMeetings);
    List<MeetingDTO> Meetings = meetingService.searchMeeting(keyword);
    assertEquals(mockedMeetings.size(), Meetings.size());
  }

  @Test
  public void testAddMeeting() {
    Meeting inputMeeting =
        new Meeting(1L, "Meeting1", LocalDateTime.of(2024, 1, 13, 12, 0), date, date);
    Meeting savedMeeting =
        new Meeting(1L, "Meeting1", LocalDateTime.of(2024, 1, 13, 12, 0), date, date);
    MeetingDTO expectedMeetingDTO =
        new MeetingDTO("Meeting1", LocalDateTime.of(2024, 1, 13, 12, 0));

    when(meetingRepository.save(inputMeeting)).thenReturn(savedMeeting);
    when(fromDOToDTO.MapMeeting(savedMeeting)).thenReturn(expectedMeetingDTO);

    MeetingDTO resultMeetingDTO = meetingService.addMeeting(inputMeeting);

    assertEquals(expectedMeetingDTO, resultMeetingDTO);
  }

  @Test
  public void testUpdateMeeting() {
    Meeting inputMeeting =
        new Meeting(1L, "Meeting1", LocalDateTime.of(2024, 1, 13, 12, 0), date, date);
    Meeting updatedMeeting =
        new Meeting(1L, "Meeting1", LocalDateTime.of(2024, 1, 13, 12, 0), date, date);
    MeetingDTO expectedMeetingDTO =
        new MeetingDTO("Meeting1", LocalDateTime.of(2024, 1, 13, 12, 0));

    when(meetingRepository.save(inputMeeting)).thenReturn(updatedMeeting);
    when(fromDOToDTO.MapMeeting(updatedMeeting)).thenReturn(expectedMeetingDTO);

    MeetingDTO resultMeetingDTO = meetingService.addMeeting(inputMeeting);

    assertEquals(expectedMeetingDTO, resultMeetingDTO);
  }
}
