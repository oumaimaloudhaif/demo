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
  @Override
  public List<MeetingDTO> getAllMeetings() {
    final List<Meeting> meetings = meetingRepository.findAll();
    List<MeetingDTO> meetingDTOS = new ArrayList<>();
    meetings.forEach(
        meeting -> {
          MeetingDTO meetingDTO = fromDOToDTO.mapMeeting(meeting);
          meetingDTOS.add(meetingDTO);
        });

    return meetingDTOS;
  }

  /**
   * @param keyword a keyword (meeting title) to search for meetings
   * @return List<MeetingDTO>
   */
  @Override
  public List<MeetingDTO> searchMeeting(String keyword) {
    final List<Meeting> meetings = meetingRepository.findByTitle(keyword);
    List<MeetingDTO> meetingDTOS = new ArrayList<>();
    meetings.forEach(
        meeting -> {
          MeetingDTO meetingDTO = fromDOToDTO.mapMeeting(meeting);
          meetingDTOS.add(meetingDTO);
        });

    return meetingDTOS;
  }

  /**
   * @param meeting the meeting object to be added
   * @return MeetingDTO
   */
  @Override
  public MeetingDTO addMeeting(Meeting meeting) {
    final Meeting savedMeeting = meetingRepository.save(meeting);

    return fromDOToDTO.mapMeeting(savedMeeting);
  }

  /**
   * @param meeting the address meeting to be updated
   * @return MeetingDTO
   */
  @Override
  public MeetingDTO updateMeeting(Meeting meeting) {
    final Meeting updatedMeeting = meetingRepository.save(meeting);

    return fromDOToDTO.mapMeeting(updatedMeeting);
  }

  /**
   * Retrieves a meeting by its ID.
   *
   * @param meetingId the ID of the meeting to retrieve
   * @return the MeetingDTO corresponding to the meeting, or null if the meeting does not exist
   */
  @Override
  public MeetingDTO getMeetingById(Long meetingId) {
    final Meeting meeting = meetingRepository.findById(meetingId).orElse(null);
    if (meeting != null) {
      return fromDOToDTO.mapMeeting(meeting);
    } else {
      return null;
    }
  }

  /**
   * Deletes a meeting by its ID.
   *
   * @param meetingId the ID of the meeting to delete
   * @return true if the meeting was deleted successfully, false otherwise
   */
  @Override
  public boolean deleteMeetingById(Long meetingId) {
    final Meeting meeting = meetingRepository.findById(meetingId).orElse(null);
    if (meeting != null) {
      meetingRepository.delete(meeting);

      return true;
    } else {

      return false;
    }
  }
}
