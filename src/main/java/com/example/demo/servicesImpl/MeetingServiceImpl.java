package com.example.demo.servicesImpl;

import com.example.demo.dto.MeetingDTO;
import com.example.demo.dto.mappers.FromDOToDTO;
import com.example.demo.entities.Meeting;
import com.example.demo.repository.MeetingRepository;
import com.example.demo.services.MeetingService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** MeetingServiceImpl */
@Service
public class MeetingServiceImpl implements MeetingService {
  @Autowired private MeetingRepository meetingRepository;
  @Autowired private FromDOToDTO fromDOToDTO;

  /** @return List<MeetingDTO> */
  public List<MeetingDTO> getAllMeetings() {
    final List<Meeting> meetings = meetingRepository.findAll();
    List<MeetingDTO> meetingDTOS = new ArrayList<>();
    meetings.forEach(
        meeting -> {
          MeetingDTO meetingDTO = fromDOToDTO.MapMeeting(meeting);
          meetingDTOS.add(meetingDTO);
        });

    return meetingDTOS;
  }

  /**
   * @param keyword a keyword (meeting title) to search for meetings
   * @return List<MeetingDTO>
   */
  public List<MeetingDTO> searchMeeting(String keyword) {
    final List<Meeting> meetings = meetingRepository.findByTitle(keyword);
    List<MeetingDTO> meetingDTOS = new ArrayList<>();
    meetings.forEach(
        meeting -> {
          MeetingDTO meetingDTO = fromDOToDTO.MapMeeting(meeting);
          meetingDTOS.add(meetingDTO);
        });

    return meetingDTOS;
  }

  /**
   * @param meeting the meeting object to be added
   * @return MeetingDTO
   */
  public MeetingDTO addMeeting(Meeting meeting) {
    final Meeting savedMeeting = meetingRepository.save(meeting);

    return fromDOToDTO.MapMeeting(savedMeeting);
  }

  /**
   * @param meeting the address meeting to be updated
   * @return MeetingDTO
   */
  public MeetingDTO updateMeeting(Meeting meeting) {
    final Meeting updatedMeeting = meetingRepository.save(meeting);

    return fromDOToDTO.MapMeeting(updatedMeeting);
  }
}
