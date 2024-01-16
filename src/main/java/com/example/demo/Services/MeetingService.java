package com.example.demo.Services;

import com.example.demo.Dto.MeetingDTO;
import com.example.demo.Entities.Meeting;
import java.util.List;

/** Meeting Service */
public interface MeetingService {
  List<MeetingDTO> getAllMeetings();

  List<MeetingDTO> searchMeeting(String keyword);

  MeetingDTO addMeeting(Meeting meeting);

  MeetingDTO updateMeeting(Meeting meeting);
}
