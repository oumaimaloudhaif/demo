package com.example.demo.services;

import com.example.demo.dto.MeetingDTO;
import com.example.demo.entities.Meeting;
import java.util.List;

/** Meeting Service */
public interface MeetingService {
  List<MeetingDTO> getAllMeetings();

  List<MeetingDTO> searchMeeting(String keyword);

  MeetingDTO addMeeting(Meeting meeting);

  MeetingDTO updateMeeting(Meeting meeting);
}
